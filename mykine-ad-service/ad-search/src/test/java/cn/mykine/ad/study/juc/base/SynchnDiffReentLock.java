package cn.mykine.ad.study.juc.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 和 ReentrantLock的区别：
 * 1.实现原理不同：synchronized是语法关键字是基于底层的monitor实现,ReentrantLock类基于AQS实现
 * 2.synchronized没有non-blocking算法，线程抢不到锁时会立即阻塞，
 *   而ReentrantLock有并支持tryLock,可实现线程抢不到琐时不立即阻塞
 * 3.synchronized无法响应中断,而ReentrantLock提供了中断能力
 *
 * */
public class SynchnDiffReentLock {

    static volatile int num=0;
    static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        /*
        Thread t1 = new Thread(()->{
            try {
                func1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        Thread.sleep(10);
        t1.interrupt();
        System.out.println("num="+num);

        */

        Thread t2 = new Thread(()->{
            func2();
        });
        t2.start();
        Thread.sleep(50);
        t2.interrupt();
        System.out.println("num="+num);
    }

    public static void func1() throws InterruptedException {
        synchronized (SynchnDiffReentLock.class){
            for (int i = 0; i < 100000000; i++) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+" Interrupted,num="+num);
                    break;
                }
                num++;
            }
        }
    }

    public static void func2(){
        ReentrantLock rLock = new ReentrantLock();
        try {
            rLock.lockInterruptibly();
            for (int i = 0; i < 1000000000; i++) {
                num++;
                if(num == 9999999){
                    System.out.println(Thread.currentThread().getName()+" to 9999999 ");
                    Thread.sleep(100);
                }
            }
            System.out.println(Thread.currentThread().getName()+" over !!!");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" interrupt !!!");
            e.printStackTrace();
        }finally {
            rLock.unlock();
            System.out.println("unlock~~~~~~~");
        }
    }
}
