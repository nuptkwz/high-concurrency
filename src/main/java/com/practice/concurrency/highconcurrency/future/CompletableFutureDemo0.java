package com.practice.concurrency.highconcurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Description
 * Date 2021/1/31 23:09
 * Created by kwz
 */
@Slf4j
public class CompletableFutureDemo0 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //complete
        testComplete();
        //exception
        Future<String> future = testException();
        future.get();
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

    private static Future<String> testException() {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(
                () -> {
                    try {
                        String tips = getTips(-2);
                        future.complete(tips);
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                }
        ).start();
        return future;
    }

    private static String getTips(int num) throws Exception {
        if (num < 0) {
            throw new Exception();
        }
        return "success";
    }
}
