package com.hy.callback.processor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hero
 * @description 主线程处理器
 * @create 2021-06-11
 **/
public class MainThreadProcessor {
    private static  MainThreadProcessor mainThreadProcessor = new MainThreadProcessor();

    /**
     * 自定义线程池
     */
    private static ExecutorService executor =
            new ThreadPoolExecutor(
                     1
                    ,   1
                    , 0L
                    , TimeUnit.MILLISECONDS
                    , new LinkedBlockingDeque<>(25000)
                    , r -> {
                Thread thread = new Thread(r);
                thread.setName("mainThreadProcessor");
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


    private MainThreadProcessor(){}



    public static MainThreadProcessor getInstance(){
        return mainThreadProcessor;
    }


    public void submit(Runnable runnable){
        executor.submit(runnable);
    }
}
