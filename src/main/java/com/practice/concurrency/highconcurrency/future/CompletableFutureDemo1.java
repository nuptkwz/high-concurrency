package com.practice.concurrency.highconcurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * CompletableFuture对象的创建
 * Date 2020/7/18 23:32
 * Created by kwz
 */
@Slf4j
public class CompletableFutureDemo1 {

    public static void main(String[] args) throws Exception {

        testRunAsync();

        testSupplyAsync();
    }

    //无返回值
    private static void testRunAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            log.info("testRunAsync begin run...");
            doSomething();
            log.info("testRunAsync run end...");
        });

        Void aVoid = future.get();
    }

    //有返回值
    private static void testSupplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            log.info("testSupplyAsync begin run...");
            doSomething();
            log.info("testSupplyAsync run end...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        log.info("future get:" + time);
    }

    private static void doSomething() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.info("exception:{}", e.getMessage());
        }
    }
}
