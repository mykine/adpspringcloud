package cn.mykine.ad.study.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * FutureTask<T>以一当二，集成Callable+Future两者的功能，实现获取多线程异步结果
 * */
public class FutureTaskTest {
    final static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName()+" callable do something ... ");
                Thread.sleep(3000);
                return Thread.currentThread().getName()+" result!";
            }
        });
        executorService.submit(futureTask);
        executorService.shutdown();
        System.out.println(Thread.currentThread().getName()+" main do sth...");
        Thread.sleep(2);
        //调用get方法获取返回值，会阻塞当前调用线程(即主线程)
        String result = futureTask.get();
        System.out.println("结果是:"+result);
    }
}
