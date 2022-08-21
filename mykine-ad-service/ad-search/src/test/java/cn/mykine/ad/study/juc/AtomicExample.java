package cn.mykine.ad.study.juc;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

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

    public static void main(String[] args) {
        AtomicExample example = new AtomicExample();
        if(AtomicExample.updater.compareAndSet(example,0,100)){
            log.info("update 0 to {}",example.getCount());
        }

        if(AtomicExample.updater.compareAndSet(example,100,200)){
            log.info("update 100 to {}",example.getCount());
        }
    }

}

