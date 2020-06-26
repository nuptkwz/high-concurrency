package com.practice.concurrency.highconcurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * Date 2020/6/27 0:30
 * Created by kwz
 */
@Slf4j
public class ThreadPoolExample5 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        executorService.schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        log.warn("schedule run");
                    }
                }, 3, TimeUnit.SECONDS
        );

//        executorService.scheduleAtFixedRate(1,3,TimeUnit.SECONDS);
//        executorService.scheduleWithFixedDelay()
        executorService.shutdown();

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.warn("timer run");
//            }
//        },new Date(),5*1000);
    }
}
