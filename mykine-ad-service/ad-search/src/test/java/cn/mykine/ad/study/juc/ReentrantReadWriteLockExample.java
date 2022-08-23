package cn.mykine.ad.study.juc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁ReentrantReadWriteLock的使用情景
 * 读写锁内部包含了读锁和写锁两个锁，在没有任何读锁、写锁的情况下才能获取到写锁
 * 适合情景：多写少读、读写均匀的情况，
 * 封装对某些数据结构的读写方法供外部使用，为防止并发安全问题，对读方法进行读锁控制，对写方法进行写锁控制
 * */
public class ReentrantReadWriteLockExample {
    final static Logger logger = LoggerFactory.getLogger(ReentrantReadWriteLockExample.class);
    volatile static Map<String, String> mapData = new HashMap<>();
    final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);
    final static Lock readLock = lock.readLock();
    final static Lock writeLock = lock.writeLock();

    static int clientRequstCount = 5000;//客户端一段时间内请求数量
    static int threadTotal = 200;//服务端限流每次处理的并发请求数量
    final static String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
    final static Semaphore semaphore = new Semaphore(threadTotal);
    final static CountDownLatch countDownLatch = new CountDownLatch(clientRequstCount);

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        ExecutorService pool = Executors.newCachedThreadPool();
        String str = "abc";
        final char[] chars = str.toCharArray();

        for (int i = 0; i < clientRequstCount; i++) {
            final int k = i;
            pool.execute(() -> {
                try {
                    //获取信号量资源，如果失败会进入Blocked状态,直到信号量资源被释放时唤起被阻塞的该线程，继续下一步操作
                    semaphore.acquire();
                    int random = ThreadLocalRandom.current().nextInt(10);
                    int y = k % 3 ;
                    if(y==0){
                        readData(random);
                    }else {
                        writeData(random);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    System.out.println(sdf.format(System.currentTimeMillis()) + ":" + "acquire-->" + k);
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
        logger.info(sdf.format(System.currentTimeMillis()) + ":"
                + "num is {},cost {} ms", cost);
    }

    public static void writeData(Integer index) {
        while (!writeLock.tryLock()) {
            ;
        }
        try {
            mapData.put(index.toString(), Thread.currentThread().getName());
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            logger.info(sdf.format(System.currentTimeMillis()) + ":"
                    + "write  {} => {} ", index.toString(), Thread.currentThread().getName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void readData(Integer index) {
        while (!readLock.tryLock()) {
            ;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            logger.info(sdf.format(System.currentTimeMillis()) + ":"
                    + "read:  {} => {} ", index.toString(),
                    mapData.get(index.toString())
            );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

}

