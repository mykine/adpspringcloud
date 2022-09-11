package cn.mykine.ad.study.juc.base;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 同步是啥：同步分为操作同步和数据同步
 *      操作同步：多个步骤操作时，后续操作要等待前序操作完成才能执行，
 *              同步代码块，是指同一时间只允许一个线程执行的代码区域，
 *              并发同步是指，多线程并发执行任务时遇到锁或阻塞信号等而进入同步点，
 *              等待锁释放或唤醒信号才能执行后续的同步块代码
 *     数据同步：集群的数据一致性传递，数据库与缓存数据一致性传递
 *临界区：即同步代码块，是指产生竞争共享资源的代码区域，需要同步操作才能实现线程安全
 * AQS是啥：AQS是JAVA提供的同步器框架，是一个抽象类，可以让开发者基于它实现自己的同步器
 * AQS源码核心：
 *
 * 案例：基于AQS实现一个同步锁工具类
 *  核心：类中维护了一个继承AQS的内部类Sync;
 *        在Sync类中重写AQS的tryAcquire()方法和tryRelease()方法，利用CAS函数实现的加锁解锁功能，
 *        供acquire()和release()方法使用;
 *       提供可对外使用的上锁方法lock(),内部调用的是acquire()方法
 *       ，unlock内部调用当然是release()方法
 *
 * */
public class AQS_Mutes {
    static int num=0;
    static AQS_Mutes myLock = new AQS_Mutes();
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            try {
                myLock.lock();
                for (int i = 0; i < 50000; i++) {
                    num++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                myLock.unlock();
            }
        });

        Thread t2 =new Thread(()->{
            try {
                myLock.lock();
                for (int i = 0; i < 50000; i++) {
                    num++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                myLock.unlock();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            t1.interrupt();
            t2.interrupt();
        }
        System.out.println("num="+num);
    }


    private MySync sync;

    private static class MySync extends AbstractQueuedSynchronizer{
            //重写AQS父级tryAcquire方法，会被acquire()方法调用
            protected boolean tryAcquire(int arg){
                return compareAndSetState(0,1);
            }

            //重写AQS父级 tryRelease 方法，会被release()方法调用
            protected boolean tryRelease(int arg){
                return compareAndSetState(1,0);
            }
    }

    public AQS_Mutes(){
        sync = new MySync();
    }

    public void lock(){
        sync.acquire(0);
    }

    public void unlock(){
        sync.release(0);
    }
}
