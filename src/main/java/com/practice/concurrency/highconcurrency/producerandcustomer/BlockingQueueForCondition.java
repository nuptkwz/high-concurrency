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

    public BlockingQueueForCondition(int size) {
        this.max = size;
        queue = new LinkedList();
    }

    public void put(Object object) throws InterruptedException {
        //多线程场景需要一定的同步措施来保证线程安全，因此需要加锁操作
        lock.lock();
        try {
            //检测队列是否已经满了
            while (queue.size() == max) {
                //此时队列已经满了，阻塞生产者线程并且释放lock锁
                notFullCondition.await();
            }
            //如果没有满则往队列里面存放数据，并且调用notEmptyCondition.signalAll()通知正在等待的消费者，
            //并且唤醒等待的消费者
            queue.add(object);
            notEmptyCondition.signalAll();
        } finally {
            //最后释放锁,否则可能会产生无法释放锁的情况
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {

        try {
            while (queue.isEmpty()) {
                notEmptyCondition.await();
            }
            Object item = queue.remove();
            notFullCondition.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueForCondition block = new BlockingQueueForCondition(20);
        for (int i = 0; i < 100; i++) {
            block.put(new Object());
        }
    }
}
