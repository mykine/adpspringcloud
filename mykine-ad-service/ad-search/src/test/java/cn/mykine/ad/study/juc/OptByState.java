package cn.mykine.ad.study.juc;

import lombok.extern.slf4j.Slf4j;

/**
 * volatile具有可见性和禁止指令重排的效果，
 *  原理是通过对变量操作附加了内存屏障的操作：
 *   写之后附加store屏障指令,将线程本地栈内存中的值刷新到主内存
 *   读之前附加load屏障指令，将主内存中的值重新读入到线程栈内存中
 *   内存屏障是CPU保障的指令，具有顺序性，保证了读写指令不重排序
 * 不适合对变量的写操作依赖当前值的情景(如i++的情景)
 * 适合状态标记的情景，一个线程设置标记，其他线程读这个标记
 * */
@Slf4j
public class OptByState {
    static volatile int flag=0;//1表示信号被发送
    public static void main(String[] args) throws InterruptedException {
        /**
         * 线程1等待信号被发送后执行任务
         * */

        Thread t1 = new Thread(()->{
            while (flag!=1){
                log.info("{}等待信号,flag={}",Thread.currentThread().getName(),flag);
            }
            if(flag==1){
               log.info("{}收到信号,开始执行任务,flag={}",Thread.currentThread().getName(),flag);
            }
        },"线程1");

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(flag==0){
                flag=1;
                log.info("{}发送信号",Thread.currentThread().getName());
            }
        },"线程2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }



}
