package com.practice.concurrency.highconcurrency.threadPool.custom;

import com.practice.concurrency.highconcurrency.threadPool.custom.impl.DiscardRejectPolicy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description
 * Date 2020/7/19 13:14
 * Created by kwz
 */
public class CustomThreadPoolTest {

    public static void main(String[] args) {
        Executor threadPool = new CustomThreadPool("custom thread pool test",
                5, 10, new ArrayBlockingQueue<>(15),
                new DiscardRejectPolicy());


        AtomicInteger num = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("running: " + System.currentTimeMillis() + ": " + num.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
