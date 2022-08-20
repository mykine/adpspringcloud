package cn.mykine.ad.study.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 10个线程编号1~10,依次报数1~10
 * 通过thread.join()实现
 * */
public class MyThread extends Thread {
    Logger logger = LoggerFactory.getLogger(MyThread.class);
    final  static String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
    private Thread preThread;//上一个编号的线程
    private int myNum;//编号

    public MyThread(Thread preThread,int myNum) {
        this.preThread = preThread;
        this.myNum = myNum;
        this.setName("线程"+myNum);
    }

    @Override
    public void run() {
        try {
            if(preThread!=null){
                /**
                 * 当前线程执行前驱线程对象的join方法，会阻塞当前线程(进入waiting状态)，
                 * 等待前驱线程执行完毕后(自动执行notifyAll)唤醒被阻塞的当前线程，当前线程被唤醒后可以继续执行后面的代码
                 *
                 * */
                preThread.join();
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            System.out.println(sdf.format(System.currentTimeMillis())+":"+Thread.currentThread().getName()+"报数:"+myNum);
        }catch (Exception e){
            logger.warn("thread[{}] run error :",Thread.currentThread().getName(),e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<MyThread> myThreads = new ArrayList<>();
        MyThread preThread = null;
        for (int i=1;i<=10;i++){
            MyThread myth = new MyThread(preThread,i);
            myThreads.add(myth);
            preThread = myth;
        }
        for (MyThread th:
             myThreads) {
            th.start();
        }
        System.out.println("ok");

    }


}
