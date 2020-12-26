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
        BlockingQueueForWaitNotify blockingQueueForWaitNotify = new BlockingQueueForWaitNotify(20);
        new Thread();
    }
}

class Producer implements Runnable {

    private BlockingQueueForWaitNotify blockingQueue;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            blockingQueue.put(i);
        }
    }
}
