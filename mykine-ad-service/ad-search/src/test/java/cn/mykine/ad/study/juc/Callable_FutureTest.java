package cn.mykine.ad.study.juc;

import java.util.concurrent.*;

/**
 * 通过Callable+Future实现获取多线程异步处理结果
 * */
public class Callable_FutureTest implements Callable<String> {

    @Override
    public String call() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" callable do something ... ");
        Thread.sleep(3000);
        return Thread.currentThread().getName()+" result!";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //使用Callable对象存放线程处理方法的返回值
        Callable_FutureTest callableTest = new Callable_FutureTest();
        //使用Future对象接收Callable存储的线程处理完后的返回值
        Future<String> future =executorService.submit(callableTest);
        executorService.shutdown();
        System.out.println(Thread.currentThread().getName()+" main do sth...");
        Thread.sleep(2);
        //调用future的get方法获取返回值，会阻塞当前调用线程(即主线程)
        String result = future.get();
        System.out.println("结果是:"+result);

    }
}
