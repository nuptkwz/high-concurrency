package com.practice.concurrency.highconcurrency.producerandcustomer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description
 * 两个线程交替打印奇数、偶数
 * Date 2020/12/26 14:52
 * Created by kwz
 */
@Slf4j
public class AlternatePrinting {
    private static Object object = new Object();
    private static AtomicInteger index = new AtomicInteger(1);
    private static volatile boolean isPrintOdd = true;

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (object) {
                    if (!isPrintOdd) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("print even number:{}", index);
                    isPrintOdd = false;
                    index.incrementAndGet();
                    object.notifyAll();
                }

            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (object) {
                    if (isPrintOdd) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("print odd number:{}", index);
                    isPrintOdd = true;
                    index.incrementAndGet();
                    object.notifyAll();
                }

            }
        }).start();
    }
}

