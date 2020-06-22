package com.practice.concurrency.highconcurrency.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Description
 * ArrayList、HashSet、HashMap都不是线程安全的
 * Date 2020/6/21 21:42
 * Created by kwz
 */
@Slf4j
public class CopyOnWriteArraySetDemo {

    private static Set<Integer> list = new CopyOnWriteArraySet<>();


    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 50;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(
                    () -> {
                        try {
                            semaphore.acquire();
                            update(count);
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
        log.info("size:{}", list.size());
    }

    private static void update(int i) {
        list.add(i);
    }
}
