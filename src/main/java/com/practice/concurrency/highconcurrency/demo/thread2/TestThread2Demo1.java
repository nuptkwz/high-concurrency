package com.practice.concurrency.highconcurrency.demo.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description
 * Date 2020/7/14 23:26
 * Created by kwz
 */
@Slf4j
public class TestThread2Demo1 {

    private static AtomicInteger num = new AtomicInteger(1);

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (num.intValue() < 100) {
                    if (num.intValue() % 2 == 1) {
                        System.out.println("奇数线程:" + num.intValue());
                        num.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (num.intValue() <= 100) {
                    if (num.intValue() % 2 == 0) {
                        System.out.println("偶数线程:" + num.intValue());
                        num.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }
        };

        t1.start();
        t2.start();
        countDownLatch.await();
    }
}
