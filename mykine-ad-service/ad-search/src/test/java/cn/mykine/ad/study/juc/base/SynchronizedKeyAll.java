package cn.mykine.ad.study.juc.base;

/**
 * synchronized 关键字的能力与原理
 * 1.可以实现上锁/解锁功能
 * 2.上锁失败时的锁升级逻辑
 *   先自旋锁一段时间，还未获得锁就升级直接进入休眠队列中进行休眠
 * 3.都每个对象都可以上锁
 *   JAVA中的每个对象都会关联一个底层的Monitor对象，作为代理操作Monitor对象的方法，
 *   Monitor对象提供了monitorEnter、monitorLeave相关的方法指令，
 *   线程通过synchronized对对象加锁就是通过monitorEnter指令抢占Obejct对应的monitor对象，
 *   抢占成功后，会将Monitor对象的owner标记为该线程的引用，
 *   抢占失败时，会将该线程放入monitor内部的等待集合中进行休眠，
 *   释放锁时，会通过monitorLeave命令将monitor对象的owner值标记位空，并且唤醒等待集合中的线程
 * 4.释放资源时唤醒所有休眠线程
 *    monitor对象内部实现了等待集合，将存储未竞争资源的线程，让他们进行休眠状态，等待被释放。
 * 5.从java6开始JVM内部优化了synchronized的锁逻辑，实现了偏向锁->轻量级锁->重量级锁，
 *   这一套锁升级的逻辑，提高了synchronized的性能
 *   偏向锁：synchronized开发者考虑实际很多情况下只有一个线程反复在执行临界区同步代码块，
 *          为此，设计了synchronized一开始时只启用偏向锁，
 *          将执行同步块的第一个线程id写入对象头markword中，
 *          当线程反复执行到sychronized修改的这个对象时，
 *          只需判断markword中偏向锁位的线程id是否是等于当前线程，等于就直接执行后面的同步块，
 *          否则再判断是否存在竞争，要升级锁
 *   轻量级锁：
 *          当出现多个线程竞争执行临界区时，JVM会将锁升级为轻量级锁，
 *          让线程通过CAS自旋操作，进行轻度的竞争
 *   重量级锁：当参与竞争的线程数量很多的时候，JVM会将轻量级锁升级为重量级锁，
 *           让线程重度的竞争底层的monitor资源
 *
 * */
public class SynchronizedKeyAll {
    static int num=0;
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            add();
        });

        Thread t2 = new Thread(()->{
            add();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("num="+num);
    }

    static void add(){
        synchronized (SynchronizedKeyAll.class){
            for (int i = 0; i < 50000; i++) {
                num++;
            }
        }
    }
}
