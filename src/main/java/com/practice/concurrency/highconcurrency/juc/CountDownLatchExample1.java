package com.practice.concurrency.highconcurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * CountDownLatch:通过一个计数来保证线程是否一直需要阻塞
 * Date 2020/5/4 10:17
 * Created by kwz
 */
@Slf4j
public class CountDownLatchExample1 {

    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(
                    () -> {
                        try {
                            doSomething(threadNum);
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("exception", e);
                        } finally {
                            countDownLatch.countDown();
                        }
                    }
            );

        }
        //调用await这个方法的类一直处于阻塞状态，所有的线程调用完之后再打印finish
        countDownLatch.await();
        log.info("finish");
        executorService.shutdown();
    }

    /**
     * Description
     * Param [threadNum]
     * return void
     */
    private static void doSomething(int threadNum) throws Exception {
        Thread.sleep(100);
        log.info("threadNum:{}", threadNum);
        Thread.sleep(100);
    }
}
