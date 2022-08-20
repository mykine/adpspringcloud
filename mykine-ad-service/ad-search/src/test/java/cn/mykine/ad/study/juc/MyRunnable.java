package cn.mykine.ad.study.juc;

import lombok.SneakyThrows;

/**
 * 目标：两个线程交替打印1~100
 * 方案：利用Synchronized加上object.wait()、object.notify()实现
 * 原理是利用了操作系统的资源监视器Monitor的特性，JAVA每个对象都对应一个monitor对象，
 * 通过Synchronized关键字对object的同步上锁、解锁对应了操作系统的monitor对象的monitorEnter()、monitorExit()操作
 * object.wait()是对占用object对应的monitor对象锁的线程进行内核级阻塞操作，
 * oject.notify()是对因object对应的monitor对象锁阻塞的线程进行内核级唤醒操作
 *
 * 小贴士：1.操作系统的线程有哪3种状态以及状态流状关系:
 *          执行状态------因抢占资源失败---->>阻塞状态-----资源被释放后重新唤醒----->>就绪状态
 *            ^                                                                |
 *            |-------------------------- 获取到CPU时间片执行指令----------------<-
 *
 *
 *       2.JVM中的线程有哪6种状态以及状态流转关系:
 *         NEW：新建状态 , new Thread()时产生
 *         RUNNABLE: 可执行状态，从RUNNABLE状态因thread.start()、object.notify()、object.notifyAll()、thread.sleep()、lock.unlock()、资源释放后到达可执行状态
 *         WAITING:等待状态，从RUNNABLE状态因wait()造成的等待
 *         TIME_WAITING:一段时间的等待状态,从RUNNABLE状态因thread.sleep()造成的一段时间内等待
 *         BLOCKED:阻塞状态,从RUNNABLE状态因lock()、synchronized进入阻塞状态
 *         TERMINTED:任务完成状态，从RUNNABLE状态顺利执行完毕进行的终结状态
 *
 * */
public class MyRunnable implements Runnable{

    volatile static int num=0;
    static Object lock = new Object();

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":synchronized--before状态: "+Thread.currentThread().getState());
        synchronized (lock){
            while (num<10){
                System.out.println(Thread.currentThread().getName()+":notify--before状态: "+Thread.currentThread().getState());
//                lock.notify();
                num++;
                System.out.println(Thread.currentThread().getName()+":num--before状态: "+Thread.currentThread().getState());
                System.out.println(Thread.currentThread().getName()+":---------------->"+num);
                System.out.println(Thread.currentThread().getName()+":num--after: "+Thread.currentThread().getState());
                lock.notify();
                if(num<10){
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName()+":wait--after: "+Thread.currentThread().getState());
            }
        }
        System.out.println(Thread.currentThread().getName()+":synchronized--after状态: "+Thread.currentThread().getState());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyRunnable(),"t1");
        Thread t2 = new Thread(new MyRunnable(),"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("ok");
    }

}
