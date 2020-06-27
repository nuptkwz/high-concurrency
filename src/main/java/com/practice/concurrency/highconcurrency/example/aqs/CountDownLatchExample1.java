package com.practice.concurrency.highconcurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * CountDownLatch:通过一个计数来保证线程是否一直需要阻塞
 * 程序执行需要等待某个场景执行后才能执行后续的操作，典型应用：并行运算。
 * 当某个处理的运算量很大时，可以将运算任务拆分成多个子任务，等到所有子任务都完成之后，
 * 父任务再拿到子任务的结果进行汇总。
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
                            //每执行一次减一个（每次调用都会执行放到finally里面）
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
