package cn.mykine.ad.study.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin通过工作窃取算法实现fork、join子任务拆解合并结果操作
 * */
public class ForkJoinTaskTest extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskTest(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0 ;
        boolean canCompute = (end-start) <= threshold;
        if(canCompute){
            //达到阈值以及以下的条件可以执行任务
            for (int i = start; i <=end ; i++) {
                sum+=i;
            }
        }else{
            //差值大于阈值，分而治之，拆成二个小任务去执行，直到子任务数值差达到阈值及以下
            int mid = (end + start) / 2;
            ForkJoinTaskTest leftTask = new ForkJoinTaskTest(start,mid);
            ForkJoinTaskTest rightTask = new ForkJoinTaskTest(mid+1,end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待子任务执行结果
            int resLeft = leftTask.join();
            int resRight = rightTask.join();

            //合并子任务结果
            sum = leftTask.join()+ rightTask.join();
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTaskTest task = new ForkJoinTaskTest(1,100);
        pool.execute(task);
        System.out.println("res="+task.get());
        pool.shutdown();
    }
}
