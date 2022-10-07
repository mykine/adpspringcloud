package cn.mykine.ad.study.juc;

import cn.mykine.ad.study.annoations.Recommend;
import cn.mykine.ad.study.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 单例的实现-枚举实现-线程安全
 *
 * */
@ThreadSafe
@Recommend
@Slf4j
public class SingletonExample5 {

    private SingletonExample5(){
//      log.info("{} 初始化实例 ",Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName()+" 初始化实例");
        String s = "123";
       char[] ss=  s.toCharArray();
    }

    public static SingletonExample5 getInstance(){
        return SingleEnum.INSTANCE.getRef();
    }

    private enum SingleEnum {
        INSTANCE;

        private SingletonExample5 ref;

        // JVM保证枚举的构造函数只会调用一次，且在被调用时进行初始化
        SingleEnum(){
            System.out.println("SingleEnum time:"+System.currentTimeMillis());
            System.out.println("SingleEnum 实例化SingletonExample5");
            ref = new SingletonExample5();
        }
        public SingletonExample5 getRef(){
            return ref;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main time:"+System.currentTimeMillis());
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(()->{
                SingletonExample5 ob = SingletonExample5.getInstance();
            });
        }
        executorService.shutdown();
        Thread.sleep(1000);
    }
}
