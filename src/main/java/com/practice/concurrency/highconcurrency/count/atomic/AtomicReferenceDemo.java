package com.practice.concurrency.highconcurrency.count.atomic;

import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Description
 * Date 2020/6/19 0:36
 * Created by kwz
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceDemo {

    private static AtomicReference<Integer> count = new
            AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);
        log.info("count:{}", count.get());
    }
}
