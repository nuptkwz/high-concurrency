package com.practice.concurrency.highconcurrency.example.singleton;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * 单例模式---懒汉模式
 * 单例实例在第一次使用时进行创建
 * Date 2020/6/21 12:46
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
public class SingletonDemo1 {

    //私有构造函数
    private SingletonDemo1() {

    }

    //单例对象
    private static SingletonDemo1 instance = null;

    //静态工厂方法获取对象
    public static SingletonDemo1 getInstance() {
        //在单线程模式下没有问题，多线程下，可能运行多次就会new多次，会产生一定的影响
        if (instance == null) {
            instance = new SingletonDemo1();
        }
        return instance;
    }
}
