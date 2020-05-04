package com.practice.concurrency.highconcurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <p>
 * Date 2020/5/4 10:17
 * Created by kwz
 */
@Slf4j
public class CountDownLatchExample2 {

    public static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(
                    () -> {
                        try {
                            doSomething(threadNum);
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("exception", e);
                        } finally {
                            //key 1
                            countDownLatch.countDown();
                        }
                    }
            );
        }
        //key 2（只有等到countDownLatch计数为0才能进行下面的操作），支持给定时间的等待，超过这个时间就不在等待了
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        exec.shutdown();
    }

    /**
     * Description
     * Param [threadNum]
     * return void
     */
    private static void doSomething(int threadNum) throws Exception {
        Thread.sleep(100);
        log.info("threadNum:{}", threadNum);
    }
}
