package cn.mykine.ad.study.juc;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 对类的字段进行原子操作
 * 通过AtomicIntegerFieldUpdater实现，要求类的字段是非static的，并且用volatile修饰
 *
 */
@Slf4j
public class AtomicExample {

    private static AtomicIntegerFieldUpdater<AtomicExample> updater
            = AtomicIntegerFieldUpdater.newUpdater(AtomicExample.class,"count");

    @Getter
    public volatile int count;

    private static Integer num;
    private static AtomicStampedReference<Integer> stampUpdater
            = new AtomicStampedReference<>(num,0);

    public static void main(String[] args) throws InterruptedException {
        AtomicExample example = new AtomicExample();
        if(AtomicExample.updater.compareAndSet(example,0,100)){
            log.info("update 0 to {}",example.getCount());
        }

        if(AtomicExample.updater.compareAndSet(example,100,200)){
            log.info("update 100 to {}",example.getCount());
        }

        stampUpdater.set(100,1);
        new Thread(()->{stampUpdater.set(100,1);}).start();
        new Thread(()->{stampUpdater.set(200,5);}).start();
        new Thread(()->{stampUpdater.set(100,3);}).start();
        Thread.sleep(3000);
        log.info("num is {},stamp is {}",stampUpdater.getReference(),stampUpdater.getStamp());
        int[] tamp = new int[1];
        Integer integer = stampUpdater.get(tamp);
        log.info("num is {},stamp is {}",integer,tamp);
    }

}

