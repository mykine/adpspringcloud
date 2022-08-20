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

/**
 * 模拟多个并发请求同时执行
 * 创建多线程默认多个请求
 * 通过CountDownLatch实现多个线程同时开始执行，模拟并发执行任务
 * */
public class AllThreadsAtSameTimeBegin {
    final static Logger logger = LoggerFactory.getLogger(AllThreadsAtSameTimeBegin.class);
    static int clientRequstCount = 100;//客户端并发请求数量
    volatile static int num;
    final  static String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
    final static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < clientRequstCount; i++) {
            final int k = i;
            pool.execute(()->{
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                try {
                    //调用了CountDownLatch对象的await()方法后会进入waiting状态，等待CountDownLatch的值减为0时被唤醒
                    countDownLatch.await();
                    System.out.println(sdf.format(System.currentTimeMillis())+":"+"add begin-->"+k);
                    //执行作业
                    add(k);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
//                    System.out.println(sdf.format(System.currentTimeMillis())+":"+"add end-->"+k);
                }
            });
        }
        pool.shutdown();

        //当前主线程将CountDownLatch置为0后，唤醒所有阻塞的线程，在同一个时间点开始执行
        countDownLatch.countDown();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        long end = System.currentTimeMillis();
        long cost = end - begin;
        Thread.sleep(5000);
        logger.info(sdf.format(System.currentTimeMillis())+":"
                +"num is {}, cost {} ms",cost);
    }

    public  static void add(Integer index){
//        synchronized (lock){
            num++;
//        map.put(index,index);
//        System.out.println(Thread.currentThread().getName()+"---"+num);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//            System.out.println(sdf.format(System.currentTimeMillis())+":"+Thread.currentThread().getName()+" ,num---"+num );
//        }
    }


}
