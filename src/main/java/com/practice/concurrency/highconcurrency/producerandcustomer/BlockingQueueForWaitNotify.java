package com.practice.concurrency.highconcurrency.producerandcustomer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Random;

/**
 * Description
 * Date 2020/12/26 11:46
 * Created by kwz
 */
@Slf4j
public class BlockingQueueForWaitNotify {

    private int maxSize;
    private LinkedList<Integer> storage;
    private final Random random = new Random();

    public BlockingQueueForWaitNotify(int size) {
        this.maxSize = size;
        storage = new LinkedList<>();
    }

    public synchronized void put(int item) throws InterruptedException {
        while (storage.size() == maxSize) {
            wait();
        }
        storage.add(item);
        notifyAll();
    }

    public synchronized int take() throws InterruptedException{
        while (storage.isEmpty()){
            wait();
        }
        int item = storage.remove();
        notifyAll();
        return item;
    }
}
