package com.practice.concurrency.highconcurrency.threadlocal;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * 非线程安全的例子
 * Date 2021/1/6 7:49
 * Created by kwz
 */
@NotThreadSafe
public class ThreadLocalDemo01 {

    //所有的线程公用一个simpleDateFormat
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
    private static ExecutorService executor = Executors.newFixedThreadPool(16);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.submit(() -> {
                String date = new ThreadLocalDemo01().date(finalI);
                System.out.println(date);
            });
        }
        executor.shutdown();
    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return simpleDateFormat.format(date);
    }
}
