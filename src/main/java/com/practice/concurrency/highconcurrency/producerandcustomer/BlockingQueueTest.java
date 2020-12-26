package com.practice.concurrency.highconcurrency.producerandcustomer;

import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * Date 2020/12/26 11:59
 * Created by kwz
 */
@Slf4j
public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueueForWaitNotify blockingQueue = new BlockingQueueForWaitNotify(20);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

@Slf4j
class Producer implements Runnable {

    private BlockingQueueForWaitNotify blockingQueue;

    public Producer(BlockingQueueForWaitNotify blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    //    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                log.info("produce begin to put");
                blockingQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

@Slf4j
class Consumer implements Runnable {

    private BlockingQueueForWaitNotify blockingQueue;

    public Consumer(BlockingQueueForWaitNotify blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                int item = blockingQueue.take();
                log.info("consumer take:{}", item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
