package com.practice.concurrency.highconcurrency.example.atomic;

import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Description
 * Date 2020/6/19 0:49
 * Created by kwz
 */
@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdaterDemo {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterDemo> updater
            = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterDemo.class, "count");

    @Getter
    public volatile int count = 100;

    private static AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();

    public static void main(String[] args) {
        if (updater.compareAndSet(demo, 100, 120)) {
            log.info("update success1, {}", demo.getCount());
        }

        if (updater.compareAndSet(demo, 100, 120)) {
            log.info("update success2, {}", demo.getCount());
        } else {
            log.info("update failed, {}", demo.getCount());
        }
    }
}
