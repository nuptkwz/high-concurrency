package com.practice.concurrency.highconcurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Description
 * Date 2020/6/27 0:30
 * Created by kwz
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            log.info("task:{}", index);
                        }
                    }
            );
        }

        executorService.shutdown();
    }
}
