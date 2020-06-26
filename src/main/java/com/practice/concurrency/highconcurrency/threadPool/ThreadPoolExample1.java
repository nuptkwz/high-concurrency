package com.practice.concurrency.highconcurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * Date 2020/6/27 0:30
 * Created by kwz
 */
@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

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
