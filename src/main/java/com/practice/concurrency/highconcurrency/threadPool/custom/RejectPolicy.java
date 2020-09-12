package com.practice.concurrency.highconcurrency.threadPool.custom;

/**
 * Description
 * Date 2020/7/19 13:06
 * Created by kwz
 */
public interface RejectPolicy {

    /**
     * 自定义拒绝策略
     * Description
     * Param [task, customThreadPool]
     * return void
     */
    void reject(Runnable task, CustomThreadPool customThreadPool);
}
