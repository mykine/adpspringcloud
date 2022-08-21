package cn.mykine.ad.study.juc;

import cn.mykine.ad.study.annoations.ThreadSafe;

/**
 * 单例的实现-恶汉模式（类装载的时候创建实例）-线程安全
 * 缺点：如果构造方法内操作步骤很多，甚至创建很多资源，那么类加载会很慢，程序启动效率低下，
 *     如果最终没有使用到该单例，就会造成资源的浪费
 * */
@ThreadSafe
public class SingletonExample2 {

    private static SingletonExample2 instance = new SingletonExample2();

    private SingletonExample2(){
    }

    public static SingletonExample2 getInstance(){
        return instance;
    }

}
