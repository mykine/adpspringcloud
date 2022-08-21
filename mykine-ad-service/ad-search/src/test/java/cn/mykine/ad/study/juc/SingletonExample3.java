package cn.mykine.ad.study.juc;

import cn.mykine.ad.study.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 单例的实现-懒汉模式-优化-双重检测同步锁实现-线程不安全
 *
 * */
@NotThreadSafe
@Slf4j
public class SingletonExample3 {

    private static SingletonExample3 instance = null;

    private SingletonExample3(){
//      log.info("{} 初始化实例 ",Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName()+" 初始化实例");
    }

    public  static SingletonExample3 getInstance(){
        //多线程调用时，可能会创建多个实例
        if(null==instance){
            synchronized (SingletonExample3.class){
                /**
                 * 仍然不是线程安全的，因为可能会发生指令重排
                 * 创建对象复制涉及3个指令：
                 *      指令1.为对象申请分配堆内存空间：memory=allocate()
                 *      指令2.初始化对象，执行构造函数：ctorInstance()
                 *      指令3.为对象引用赋值指向分配好的内存空间地址：instance=memory
                 * 上面3个指令没有Happens-Before要求，多线程情况下，可能会发生指令重排
                 * 例如顺序变成：指令1 -> 指令3 -> 指令2
                 * 线程A执行到指令2时,instance不为null了，
                 * 此时线程B刚好直行到第一次的 if(null==instance) ，
                 * 发现false，就直接return instance
                 * 实际线程A还未执行指令3，也就对象还未完成初始化，线程B在使用实例时就会出错
                 * 故这种情景下虽然使用同步锁，但仍然线程不安全
                 * */
                if(null==instance){
                    instance = new SingletonExample3();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                SingletonExample3 ob = SingletonExample3.getInstance();
            });
        }
        executorService.shutdown();
        Thread.sleep(1000);
    }
}
