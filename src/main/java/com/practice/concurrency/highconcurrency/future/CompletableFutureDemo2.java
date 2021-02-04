package com.practice.concurrency.highconcurrency.future;

import java.util.concurrent.*;

/**
 * Description
 * supplyAsync&runAsync的使用例子
 * Date 2021/2/3 0:09
 * Created by kwz
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testCallable();
        testAllOf();
    }

    private static void testCallable() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> callFuture = executorService.submit(new Callable<String>() {
                                                               @Override
                                                               public String call() throws Exception {
                                                                   System.out.println("begin to print");
                                                                   return "call success";
                                                               }
                                                           }
        );
        String s = callFuture.get();
        System.out.println(s);
    }

    private static void testAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> futureOne = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("future one");
            }
            return "future one";
        });

        CompletableFuture<String> futureTwo = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("future two");
            }
            return "future two";
        });

        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(futureOne, futureTwo);
        System.out.println(allOfFuture.get());

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(futureOne, futureTwo);
        System.out.println(anyOfFuture.get());
    }
}
