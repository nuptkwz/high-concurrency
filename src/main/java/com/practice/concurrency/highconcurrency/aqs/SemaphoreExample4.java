package com.practice.concurrency.highconcurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * semaphore.tryAcquire：尝试获取许可
 * 控制并发执行的数量（20），每次20个线程执行，一块一块的执行
 * Date 2020/5/4 10:17
 * Created by kwz
 */
@Slf4j
public class SemaphoreExample4 {

    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(
                    () -> {
                        try {
                            if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                                doSomething(threadNum);
                                //释放许可
                                semaphore.release();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("exception", e);
                        }
                    }
            );
        }
        log.info("finish");
        executorService.shutdown();
    }

    /**
     * Description
     * Param [threadNum]
     * return void
     */
    private static void doSomething(int threadNum) throws Exception {
        log.info("threadNum:{}", threadNum);
        Thread.sleep(1000);
    }
}
