package com.practice.concurrency.highconcurrency.example.volitate;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description
 * 1.volatile这种操作对于这种加操作是不安全的（不适合计数的场景）
 * 2.volatile不具备原子性
 *
 * 使用volatile一般具备两种场景：
 * 1.对变量的写操作不依赖于当前值
 * 2.该变量没有包含在具有其他变量的不变式中
 * Date 2020/6/18 22:45
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
public class VolatileDemo {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 50;
    public static volatile int count = 0;

    private static void add() {
        //被volatile修饰的时候，执行三步
        //1.取出当前内存的count值
        //2.+1
        //2.重新写会主存
        count++;
        //两个线程操作时候，他们都读取到了count的值，又同时刷新到主存，这样就丢掉了一次+操作
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
