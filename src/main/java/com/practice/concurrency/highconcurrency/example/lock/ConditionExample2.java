package com.practice.concurrency.highconcurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 * Date 2020/7/13 23:52
 * Created by kwz
 */
@Slf4j
public class ConditionExample2 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        for (int i = 0; i < 100; i++) {
            final int cur = i;
            new Thread(() -> {
                try {
                    reentrantLock.lock();
                    log.info("thread1" + ">>>>>>" + cur); //1
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                log.info("get signal"); //4
                reentrantLock.unlock();
            }).start();


            new Thread(() -> {
                reentrantLock.lock();
                log.info("thread2" + ">>>>>>" + cur);//2
                condition.signal();
//                log.info("send signal ~"); //3
                reentrantLock.unlock();
            }).start();
        }
    }
}
