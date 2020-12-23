package com.practice.concurrency.highconcurrency.producerandcustomer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Description
 * 利用BlockingQueue实现生产者、消费者
 * Date 2020/12/6 22:13
 * Created by kwz
 */
@Slf4j
public class BlockingQueueDemo1 {

    public static void main(String[] args) {
        //创建ArrayBlockingQueue类型的BlockingQueue
        BlockingQueue<Object> arrayQueue = new ArrayBlockingQueue<>(10);

        Runnable producer = () -> {
            while (true) {
                try {
                    //生产者往队列中存放数据
                    log.info("execute arrayQueue put......");
                    arrayQueue.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //启动两个生产者线程
        new Thread(producer).start();
        new Thread(producer).start();

        Runnable customer = () -> {
            while (true) {
                try {
                    log.info("execute arrayQueue take......");
                    //消费者从队列中取出数据
                    arrayQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //启动两个消费者线程
        new Thread(customer).start();
        new Thread(customer).start();
    }
}
