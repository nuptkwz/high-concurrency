package com.practice.concurrency.highconcurrency.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description
 * 一个许可的情况
 * 控制同一时间并发线程的数目
 * 控制并发执行的数量（20），每次20个线程执行，一块一块的执行
 * Date 2020/5/4 10:17
 * Created by kwz
 */
@Slf4j
public class SemaphoreExample1 {

    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        //20表示允许的并发数
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(
                    () -> {
                        try {
                            //拿到许可
                            semaphore.acquire();
                            doSomething(threadNum);
                            //释放许可
                            semaphore.release();
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
