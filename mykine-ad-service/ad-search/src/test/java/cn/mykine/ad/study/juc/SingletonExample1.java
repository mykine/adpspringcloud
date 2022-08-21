package cn.mykine.ad.study.juc;

import cn.mykine.ad.study.annoations.NotThreadSafe;

/**
 * 单例的实现-懒汉模式（第一次使用时创建实例）-线程不安全
 * */
@NotThreadSafe
public class SingletonExample1 {

    private static SingletonExample1 instance = null;

    private SingletonExample1(){
    }

    public static SingletonExample1 getInstance(){
        //多线程调用时，可能会创建多个实例
        if(null==instance){
            instance = new SingletonExample1();
        }
        return instance;
    }

}
