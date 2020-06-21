package com.practice.concurrency.highconcurrency.commonUnsafe;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description
 * StringBuilder 不安全的类
 * StringBuffer 线程安全的类
 * Date 2020/6/21 21:13
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
public class StringDemo {

    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 50;
    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(
                    () -> {
                        try {
                            semaphore.acquire();
                            update();
                            semaphore.release();
                        } catch (InterruptedException e) {
                            log.error("exception", e);
                        }
                        countDownLatch.countDown();
                    }
            );
        }
        countDownLatch.await();
        //线程池用完之后关闭它
        executorService.shutdownNow();
        log.info("stringBuilder:{}", stringBuilder.length());
    }

    private static void update() {
        stringBuilder.append("1");
    }
}
