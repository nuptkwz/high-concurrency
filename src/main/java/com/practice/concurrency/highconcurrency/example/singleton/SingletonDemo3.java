package com.practice.concurrency.highconcurrency.example.singleton;

import com.practice.concurrency.highconcurrency.annoation.NotRecommend;
import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * 单例模式---懒汉模式
 * 通过加入 synchronized 关键词可以实现线程安全的，但是加入synchronized之后失去了并发性
 * 带来的性能上的开销
 * 单例实例在第一次使用时进行创建
 * Date 2020/6/21 12:46
 * Created by kwz
 */
@Slf4j
@ThreadSafe
@NotRecommend
public class SingletonDemo3 {

    //私有构造函数
    private SingletonDemo3() {

    }

    //单例对象
    private static SingletonDemo3 instance = null;

    //静态工厂方法获取对象
    public static synchronized SingletonDemo3 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo3();
        }
        return instance;
    }

}
