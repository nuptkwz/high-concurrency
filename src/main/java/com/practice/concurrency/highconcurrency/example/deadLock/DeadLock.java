package com.practice.concurrency.highconcurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * Date 2020/6/27 23:39
 * Created by kwz
 */
@Slf4j
public class DeadLock implements Runnable {

    public int flag = 1;
    //静态对象是类的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    log.error("o1 exception", e);
                }

                synchronized (o2){
                    log.info("1");
                }
            }
        }

        if (flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    log.error("o2 exception", e);
                }

                synchronized (o1){
                    log.info("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();

        td1.flag = 1;
        td2.flag = 0;

        //td1,td2都处于可执行状态，但jvm线程调度先执行哪个线程是不确定的
        //td2的run()可能在td1的run()之前运行
        new Thread(td1).start();
        new Thread(td2).start();
    }
}
