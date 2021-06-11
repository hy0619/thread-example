package com.hy.callback.processor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hero
 * @description 异步处理器
 * @create 2021-06-11
 **/
public final class AsyncProcessor {
    private static  AsyncProcessor syncThreadProcessor = new AsyncProcessor();

    /**
     * 自定义线程池
     */
    private static ExecutorService executor =
            new ThreadPoolExecutor(
                     Runtime.getRuntime().availableProcessors() + 1
                    , (Runtime.getRuntime().availableProcessors() + 1) * 25
                    , 0L
                    , TimeUnit.MILLISECONDS
                    , new LinkedBlockingDeque<>(25000)
                    , r -> {
                        Thread thread = new Thread(r);
                        thread.setName("syncThreadProcessor");
                        if (thread.isDaemon()){
                            thread.setDaemon(false);
                        }
                        if (thread.getPriority() != Thread.NORM_PRIORITY){
                            thread.setPriority(Thread.NORM_PRIORITY);
                        }
                        return thread;
                        }
                    , new ThreadPoolExecutor.AbortPolicy()
            );


    private AsyncProcessor(){}



    public static AsyncProcessor getInstance(){
        return syncThreadProcessor;
    }

    /**
     * @description: 异步执行
     * @param r
     * @return: void
     * @author: Hero
     * @date: 2021/6/11
     */
    public void submit(IAsync r){
        if(r == null) {return ;}
        executor.submit(() -> {
            r.doBefore();
            r.doOperate();
            //主线程处理
            MainThreadProcessor.getInstance().submit(() -> r.doFinish());

        });
    }

}
