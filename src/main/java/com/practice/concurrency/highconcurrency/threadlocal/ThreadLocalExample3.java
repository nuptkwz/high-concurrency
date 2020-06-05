package com.practice.concurrency.highconcurrency.threadlocal;

import lombok.Getter;
import lombok.Setter;

/**
 * Description(普通多线程代码)
 * 线程隔离例子
 * 在多线程并发场景下，每个线程中的变量都是相互独立的
 * 线程A：设置（变量1）      获取（变量1）
 * 线程B：设置（变量2）      获取（变量2）
 * Date 2020/6/3 22:03
 * Created by kwz
 */
public class ThreadLocalExample3 {

    @Getter
    @Setter
    private String content;

    public static void main(String[] args) {
        ThreadLocalExample3 example = new ThreadLocalExample3();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(
                    () -> {
                        //每个线程存一个变量，过一会儿取这个变量
                        synchronized (ThreadLocalExample3.class) {
                            example.setContent(Thread.currentThread().getName() + "的数据");
                            System.out.println("----------------------------------------");
                            System.out.println(Thread.currentThread().getName() + "---->" + example.getContent());
                        }
                    }
            );
            //0->4一共有5个线程
            thread.setName("线程" + i);
            thread.start();
        }
    }
}
