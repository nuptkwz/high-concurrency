package com.practice.concurrency.highconcurrency.example.count;

import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * Description
 * CAS的操作是在一个死循环内不断的尝试修改目标值，直到修改成功，如果竞争不激烈的时候，它修改
 * 成功的概率很高，否则的话，修改失败的概率很高，在修改失败的情况下，这些原子操作会进行多次操作，
 * 因此性能会受到影响
 * Date 2020/6/18 22:45
 * Created by kwz
 */
@Slf4j
@ThreadSafe
public class LongAdderTest {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //有个知识点，就是对于普通类型的long和double对象，jvm允许将64位的读操作或者写操作，拆成两个
    //32位的操作，那么LongAdder这个类的实现是基于什么思想呢？
    //LongAdder的核心是将热点数据分离，比如它可以将AtomicLong内部的value分离成一个数组，每个线程
    //访问时通过hash等算法，映射到其中一个数字进行计数，而最终的计数结果为这个数组的累加
    //其中热点数据value它会被分离成多个单元的ceil，每个ceil独立维护内部的值，当前对象的值由内部的
    //ceil累计合成，这样热点就进行了有效的分离，并提高了并行度.这样LongAdder相当于在AtomicLong
    //的基础上将单点的更新压力分散到各个节点上，在低并发场景下通过对base的更新可以和Atomic的性能基本
    //保持一致，而在高并发的时候，则通过分散，提高了性能。

    //LongAdder缺点：在统计的时候，如果有并发更新，可能会导致统计的数据有些误差，在实际的高并发
    //场景下，我们优先使用LongAdder，当然在并发很低，使用Atomic效率会更高一些。而且如果需要准确的
    //数值，这时候AtomicLong会更加适合一点。
    public static LongAdder count = new LongAdder();

    private static void add() {
        count.increment();
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(
                    () -> {
                        try {
                            semaphore.acquire();
                            add();
                            semaphore.release();
                        } catch (InterruptedException e) {
                            log.error("exception", e);
                        }
                        countDownLatch.countDown();
                    }
            );
        }
        countDownLatch.await();
        //线程池用完之后关闭它
        executorService.shutdownNow();
        log.info("count:{}", count);
    }
}
