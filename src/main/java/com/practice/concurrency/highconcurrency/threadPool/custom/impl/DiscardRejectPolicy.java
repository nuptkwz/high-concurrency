package com.practice.concurrency.highconcurrency.threadPool.custom.impl;

import com.practice.concurrency.highconcurrency.threadPool.custom.CustomThreadPool;
import com.practice.concurrency.highconcurrency.threadPool.custom.RejectPolicy;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * 丢弃当前任务
 * Date 2020/7/19 13:08
 * Created by kwz
 */
@Slf4j
public class DiscardRejectPolicy implements RejectPolicy {

    @Override
    public void reject(Runnable task, CustomThreadPool customThreadPool) {
        log.info("discard this task!");
    }
}
