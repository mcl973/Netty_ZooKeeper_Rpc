package ZK_Netty_Rpc.Extras;

import ZK_Netty_Rpc.Info.argsInfo;

import java.util.Stack;
import java.util.concurrent.*;

public class MyThreadPool {
    public static volatile ThreadPoolExecutor tpe;
    public static Object o = new Object();
    static class MyRejectHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            /**
             *   出现在这里了肯定是失败了的，那么有一次机会再次的去添加任务，但是得先睡眠一会
             *   这里相当于是一个缓存带让系统缓一口气，再看看有没有空余的
             */
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.submit(r);
        }
    }

    public static ThreadPoolExecutor getDefaultThreadPoolExcutor(){
        if (tpe == null) {
            synchronized (o) {
                if (tpe == null) {
                    tpe = new ThreadPoolExecutor(argsInfo.Default_Thread_Number,
                            argsInfo.Default_Thread_Number,
                            10,
                            TimeUnit.SECONDS,
                            new ArrayBlockingQueue<Runnable>(argsInfo.Default_Thread_Number),
                            new MyRejectHandler());
                    return tpe;
                }
            }
        }
        return tpe;
    }
    public static ThreadPoolExecutor getMySelfThreadPoolExecutor(int coreThreadSize, int maxYhreadSize, int KeepTime, TimeUnit unit,
                                                                 BlockingQueue<Runnable> waitingQueue,
                                                                 ThreadFactory threadFactory,
                                                                 RejectedExecutionHandler rejectedExecutionHandler){
        return new ThreadPoolExecutor(coreThreadSize,maxYhreadSize,KeepTime,unit,waitingQueue,threadFactory,rejectedExecutionHandler);
    }
}
