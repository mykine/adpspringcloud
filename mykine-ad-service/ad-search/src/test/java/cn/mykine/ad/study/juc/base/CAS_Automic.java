package cn.mykine.ad.study.juc.base;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发编程的基础概念：
 * 1.原子操作：不可再分的操作，例如CPU指令
 * 2.临界区：多个线程操作共享资源的代码块
 * 3.竞争条件：多个线程执行临界区代码形成对共享资源操作的竞争关系
 * 解决竞争的方法：
 *     (1)减少/消除竞争：将任务分解成子任务，每个子任务交给单独的线程处理,最终将结果由主线程汇总
 *     (2)使用CAS实现原子性操作,拒绝了竞争条件，
 *          并可通过以下实例，循环空操作不断尝试执行CAS指令，实现多线程并发安全操作共享资源
 *          while(!cas(&i,i,i+1)){
 *
 *          }
 *     (3)互斥：比如加锁操作,
 *      基于AQS实现的锁ReentrantLock的互斥实现原理就是利用了CAS
 *      int lock = 0;
 *      //上锁
 *      enter(){
 *          while(!cas(&lock,0,1)){
 *
 *          };
 *      }
 *
 *      //临界区
 *      i++;
 *
 *      //解锁
 *      unlock(){
 *          lock = 0;
 *      }
 *
 * 4.CAS概念：compare and swap/set 比较再交换/设置值
 *   boolean cas(&a,认为a变量的内存值，要修改的a最终值);
 *   线程操作共享变量a时，会传入a的地址，认为a在内存中的值a1，以及要修改a的目标值a2,
 *   cas函数会将a1值与a在内存中当前真实的值a3比较，相等就修改成目标值a2，返回true,
 *   不相等就说明a被其他线程修改过了，就直接返回false
 *  5.CAS的原子操作原理：由CPU指令保证原子性，
 *   JAVA中提供了Unsafe类去调用底层的CPU相关CAS指令
 *
 *  6.CAS和AQS的关系：
 *    AQS是CAS的一种实现应用，AQS的锁互斥功能中的加锁的原子性操作就是通过CAS实现的，
 *    例如AQS中的compareAndSetState方法中调用了unsafe.compareAndSwapInt方法实现了对变量的原子性操作
 *    而基于AQS的锁功能就是利用了这个原理实现了加锁
 * */
public class CAS_Automic {

  public static void main(String[] args) {
    ReentrantLock lock  = new ReentrantLock(false);
    lock.lock();
    lock.unlock();
  }

}
