package cn.mykine.ad.study.juc;

import cn.mykine.ad.study.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 单例的实现-懒汉模式-优化-volatile+双重检测同步锁实现-线程安全
 *
 * */
@NotThreadSafe
@Slf4j
public class SingletonExample4 {

    private volatile static SingletonExample4 instance = null;

    private SingletonExample4(){
//      log.info("{} 初始化实例 ",Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName()+" 初始化实例");
    }

    public static SingletonExample4 getInstance(){
        /**
         * 因为instance变量用了volatile修饰，
         * 涉及instance的代码指令会禁止其附近上下的指令重排序
         *
         * */
        if(null==instance){
            synchronized (SingletonExample4.class){
                if(null==instance){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(()->{
                SingletonExample4 ob = SingletonExample4.getInstance();
            });
        }
        executorService.shutdown();
        Thread.sleep(1000);
    }
}
