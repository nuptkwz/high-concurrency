package com.practice.concurrency.highconcurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * Date 2020/6/20 1:01
 * Created by kwz
 */
@Slf4j
public class SynchronizedDemo {

    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(
                () -> {
                    synchronizedDemo.test1();
                }
        );
        executorService.execute(
                () -> {
                    synchronizedDemo.test1();
                }
        );
    }

    //修饰一个代码块
    public void test1() {
        //同步代码块是作用于当前对象，不同对象的代码块是互不影响的
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1-{}", i);
            }
        }
    }

    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2-{}", i);
        }
    }
}
