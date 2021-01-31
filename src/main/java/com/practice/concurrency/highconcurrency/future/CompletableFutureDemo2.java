package com.practice.concurrency.highconcurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Description
 * Date 2021/1/31 23:09
 * Created by kwz
 */
@Slf4j
public class CompletableFutureDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testComplete();
    }

    private static void testComplete() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = new CompletableFuture<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                log.info("CompletableFuture 可以监控这个任务的执行");
                future.complete("任务返回");
            }
        });
        System.out.println(future.get());
    }
}
