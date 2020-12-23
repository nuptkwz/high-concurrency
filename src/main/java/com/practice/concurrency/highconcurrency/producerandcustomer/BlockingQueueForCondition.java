package com.practice.concurrency.highconcurrency.producerandcustomer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 * 利用Condition实现生产者、消费者
 * Date 2020/12/23 22:05
 * Created by kwz
 */
@Slf4j
public class BlockingQueueForCondition {

    private Queue queue;
    private int max = 16;
    private ReentrantLock lock = new ReentrantLock();
    //在lock锁的基础上创建两个条件，notEmptyCondition：队列没有空的条件，notFullCondition队列没有满的条件
    private Condition notEmptyCondition = lock.newCondition();
    private Condition notFullCondition = lock.newCondition();

    public BlockingQueueForCondition(int size){
        this.max = size;
        queue = new LinkedList();
    }

    public static void main(String[] args) {

    }
}
