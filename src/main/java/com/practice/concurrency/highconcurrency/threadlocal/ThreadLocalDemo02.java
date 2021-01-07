package com.practice.concurrency.highconcurrency.threadlocal;

import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * Date 2021/1/6 21:19
 * Created by kwz
 */
@ThreadSafe
public class ThreadLocalDemo02 {

    private static ExecutorService executor = Executors.newFixedThreadPool(16);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.submit(() -> {
                String date = new ThreadLocalDemo02().date(finalI);
                System.out.println(date);
            });
        }
        //用完之后要remove掉，以防止内存泄漏
        ThreadSafeFormatter.dateFormatThreadLocal.remove();
        executor.shutdown();
    }

    public String date(int seconds) {
        //如果simpleDateFormat被static修饰，是类共享的，那么及时用threadLocal也是无法解决线程安全问题
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }
}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }
    };
}
