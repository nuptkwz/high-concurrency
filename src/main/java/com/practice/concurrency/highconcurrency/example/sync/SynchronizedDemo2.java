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
public class SynchronizedDemo2 {

    public static void main(String[] args) {
        SynchronizedDemo2 synchronizedDemo = new SynchronizedDemo2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(
                () -> {
                    synchronizedDemo.test1(1);
                }
        );
        executorService.execute(
                () -> {
                    synchronizedDemo.test1(2);
                }
        );
    }

    //修饰一个代码块
    public static void test1(int j) {
        //同步代码块是作用于当前对象，不同对象的代码块是互不影响的
        synchronized (SynchronizedDemo2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1-{},{}", j, i);
            }
        }
    }

    //修饰一个静态方法
    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2-{},{}", j, i);
        }
    }
}
