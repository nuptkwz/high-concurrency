package com.practice.concurrency.highconcurrency.lock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description 公平锁和非公平锁的演示
 * Date 2021/1/3 18:06
 * Created by kwz
 */
@Slf4j
public class FairAndUnFairDemo {
    private static final int THRESHOLD_NUMBERS = 10;

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[THRESHOLD_NUMBERS];
        for (int i = 0; i < THRESHOLD_NUMBERS; i++) {
            threads[i] = new Thread(new Job(printQueue), "thread" + i);
        }

        for (int i = 0; i < THRESHOLD_NUMBERS; i++) {
            threads[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

@Slf4j
class Job implements Runnable {

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        log.info("begin to print job:{}", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        log.info("print job finished:{}", Thread.currentThread().getName());
    }
}

@Slf4j
class PrintQueue {
    //默认是非公平锁
    private final Lock queueLock = new ReentrantLock(true);

    public void printJob(Object document) {
        queueLock.lock();
        try {
            long duration = (long) (Math.random() * 10000);
            log.info("{}:printQueue:,printing a job during {} second/n",
                    Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.info("message:{}", e.getMessage());
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

        queueLock.lock();
        try {
            long duration = (long) (Math.random() * 10000);
            log.info("{}:printQueue:,printing a job during {} second/n",
                    Thread.currentThread().getName(), (duration / 1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.info("message:{}", e.getMessage());
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }
}
