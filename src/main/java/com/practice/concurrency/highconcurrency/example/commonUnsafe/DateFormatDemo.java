package com.practice.concurrency.highconcurrency.example.commonUnsafe;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description
 * Date 2020/6/21 21:19
 * 使用SimpleDateFormat多线程并发执行的时候会出现很多个异常
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
public class DateFormatDemo {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 50;

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
    }

    private static void update() {
        try {
            simpleDateFormat.parse("20200621");
        } catch (ParseException e) {
            log.error("parse exception", e);
        }
    }
}
