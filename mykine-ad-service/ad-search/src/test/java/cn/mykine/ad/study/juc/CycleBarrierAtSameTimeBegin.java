package cn.mykine.ad.study.juc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * 模拟每积累到N个线程就一起开始执行任务，
 * 前一批执行完后，后一批继续，
 * 使用CycleBarrier实现循环批次控制多个线程同时执行
 *
 * */
public class CycleBarrierAtSameTimeBegin {
    final static Logger logger = LoggerFactory.getLogger(CycleBarrierAtSameTimeBegin.class);

    static CyclicBarrier cyclicBarrier
            = new CyclicBarrier(3);//设置每次达到5个线程就唤醒这个3个线程开始执行任务

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            final int num = i;
            executorService.execute(()->{
                try {
                    CycleBarrierAtSameTimeBegin.race(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        Thread.sleep(3000);
    }

    static void race(int num) throws Exception {
        Thread.sleep(200);
        logger.info("{} done1",Thread.currentThread().getName());
        cyclicBarrier.await();
        logger.info("{} begin do2",Thread.currentThread().getName());

    }


}
