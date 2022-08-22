package cn.mykine.ad.study.juc;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟客户端一小段时间内同时发起5000个请求，服务器限制并发处理200个请求的情景
 * 业务功能：使用ReentrantLock对 num 进行累加计数,实现线程安全操作
 * ReentrantLock与synchronized的区别：
 * 1.实现原理不同：ReentrantLock通过AQS实现，synchronized通过底层的monitor实现
 * 2.ReentrantLock可以实现公平锁和非公平锁，sychronized只能实现非公平锁
 * 3.ReentrantLock可以通过tryLock方法实现自旋锁，sychronized无法实现，因为sychronized关键字获取锁失败时直接会进入内核级的阻塞状态
 * 4.Reentrantlock提供lockInterruptibly()实现响应中断机制，sychronized没有此机制
 * 5.Reentrantlock提供了Condition类，可以分组唤醒需要唤醒的线程
 *
 * */
public class CountByLockExample {
    final static Logger logger = LoggerFactory.getLogger(CountByLockExample.class);
    volatile static int num=0;
    static int clientRequstCount = 5000;//客户端一段时间内请求数量
    static int threadTotal = 200;//服务端限流每次处理的并发请求数量
    final static ReentrantLock lock = new ReentrantLock(true);//构造函数传入true表示使用公平锁
    final  static String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
    final static Semaphore semaphore = new Semaphore(threadTotal);
    final static CountDownLatch countDownLatch = new CountDownLatch(clientRequstCount);
    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < clientRequstCount; i++) {
            final int k = i;
            pool.execute(()->{
                try {
                    //获取信号量资源，如果失败会进入Blocked状态,直到信号量资源被释放时唤起被阻塞的该线程，继续下一步操作
                    semaphore.acquire();

                    //执行作业
                    add(k);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    System.out.println(sdf.format(System.currentTimeMillis())+":"+"acquire-->"+k);
//                    logger.info("");
                    semaphore.release();
                }
                //当前线程作业执行完成后调用CountDownLatch的减值操作
                countDownLatch.countDown();
            });
        }
        pool.shutdown();

        //当前主线程调用了CountDownLatch对象的await()方法后会进入waiting状态，等待CountDownLatch的值减为0时被唤醒
        countDownLatch.await();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        long end = System.currentTimeMillis();
        long cost = end - begin;
//        Thread.sleep(5000);
        logger.info(sdf.format(System.currentTimeMillis())+":"
                +"num is {},cost {} ms",num,cost);
    }

    public  static void add(Integer index){
        /*
        lock.lock();//获取锁失败时直接进入阻塞状态
        try {
            num++;

            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            System.out.println(sdf.format(System.currentTimeMillis())+":"+Thread.currentThread().getName()+" ,num---"+num );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        */

        while(!lock.tryLock()){
            /**
             * 通过tryLock尝试获取锁，获取失败不会立即阻塞，而是返回false，可以让程序员控制下一步操作，比如实现自旋锁
             * */
//            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//            System.out.println(sdf.format(System.currentTimeMillis())+":"+Thread.currentThread().getName()+" 尝试获取锁失败,自旋操作..." );
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            num++;
            System.out.println(sdf.format(System.currentTimeMillis())+":"+Thread.currentThread().getName()+" 获得锁,num---"+num );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(lock.isLocked()){
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                System.out.println(sdf.format(System.currentTimeMillis())+":"+Thread.currentThread().getName()+" ,释放锁" );
                lock.unlock();
            }
        }
    }


}
