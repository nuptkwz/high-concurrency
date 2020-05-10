package com.practice.concurrency.highconcurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * Date 2020/5/10 16:06
 * Created by kwz
 */
@Slf4j
public class CyclicBarrierExample1 {

    /**
     * 给定一个值告诉当前线程要有多少个值来等待
     */
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(
                    () -> {
                        try {
                            race(threadNum);
                        } catch (Exception e) {
                            log.error("exception", e);
                        }
                    }
            );
        }
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        /**
         * 当它达到我们的数目之后了，await后面的就可以执行了
         */
        barrier.await();
        log.info("{} continue", threadNum);
    }
}
