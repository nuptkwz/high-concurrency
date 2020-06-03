package com.practice.concurrency.highconcurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Description
 * Date 2020/5/13 22:55
 * Created by kwz
 */
@Slf4j
public class FutureTaskExample1 {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something...");
            Thread.sleep(5000);
            return "finished";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        //如何方法没有结束的话，会一直阻塞在这里
        String result = future.get();
        log.info("result,{}", result);
    }
}
