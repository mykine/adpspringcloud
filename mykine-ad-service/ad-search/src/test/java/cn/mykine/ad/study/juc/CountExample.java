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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟客户端一小段时间内同时发起5000个请求，服务器限制并发处理200个请求的情景
 * 业务功能：对 num 进行累加计数
 *
 * 通过创建5000个线程模拟5000个请求
 * 通过创建200个信号量模拟限流200个请求的并发处理
 * 当设置的信号量数目是1时，相当于单线程一个一个的处理请求
 * 通过CountDownLatch让主线程确保所有线程都结束后再继续工作，替代Thread.sleep()
 *
 * 通过将num改为原子整型变量，实现线程安全的操作
 * 原子类的底层是CAS保证了原子操作
 * 源码： do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 *
 *    var1是原子对象，
 *    var2是当前线程栈中操作的对象的值，
 *    var4是要新增的值1，
 *    var5是期望的内存中的值，通过JVM底层的C++函数获取的原子对象在内存中当前最新的值，
 *    var5 + var4是最终要修改的值
 *    compareAndSwapInt()方法是JVM底层C++函数，会判断var2和var5是否相等，
 *    如果不相等说明有其他线程修改了这个原子对象，会从新从内存获取最新的值赋给var5，并且会从内存重新读取对象值到当前线程栈中的本地变量中并传递给var2,进行下一轮比较，
 *    直到var2和var5相等就执行更新操作成var5 + var4最终值
 *
 * */
public class CountExample {
    final static Logger logger = LoggerFactory.getLogger(CountExample.class);
    static int clientRequstCount = 5000;//客户端一段时间内请求数量
    static int threadTotal = 200;//服务端限流每次处理的并发请求数量
//    volatile static int num;
    static AtomicInteger num = new AtomicInteger(0);
    final static Object lock = new Object();
    static Map<Integer,Integer> map = Maps.newHashMap();
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
                +"num is {},map.size is {},cost {} ms",num.get(),map.size(),cost);
    }

    public  static void add(Integer index){
//        synchronized (lock){
//            num++;
          num.incrementAndGet();
//        map.put(index,index);
//        System.out.println(Thread.currentThread().getName()+"---"+num);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            System.out.println(sdf.format(System.currentTimeMillis())+":"+Thread.currentThread().getName()+" ,num---"+num );
//        }
    }


}
