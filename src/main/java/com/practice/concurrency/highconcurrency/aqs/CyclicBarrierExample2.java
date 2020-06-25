package com.practice.concurrency.highconcurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Description
 * Date 2020/5/10 16:06
 * Created by kwz
 */
@Slf4j
public class CyclicBarrierExample2 {

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
        executorService.shutdown();
    }

    /***
     * Description
     * 多个异常可以使用 InterruptedException | BrokenBarrierException e
     * Param [threadNum]
     * return void
     */
    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        //当它达到我们的数目之后了，await后面的就可以执行了
        //支持等待时间的
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.warn("InterruptedException", e);
        } catch (BrokenBarrierException e) {
            log.warn("BrokenBarrierException", e);
        } catch (TimeoutException e) {
            log.warn("TimeoutException", e);
        }
        log.info("{} continue", threadNum);
    }
}
