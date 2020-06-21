package com.practice.concurrency.highconcurrency.count.singleton;

import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * 单例模式---饿汉模式
 * 单例实例在类装载使用时进行创建(它能保证线程安全)
 * Date 2020/6/21 12:46
 * Created by kwz
 */
@Slf4j
@ThreadSafe
public class SingletonDemo2 {

    //饿汉模式缺点--如果类的构造方法中存在着过多的处理，会导致这个类加载的时候特别慢

    //私有构造函数
    private SingletonDemo2() {

    }

    //单例对象
    private static SingletonDemo2 instance = new SingletonDemo2();

    //静态工厂方法获取对象
    public static SingletonDemo2 getInstance() {
        return instance;
    }

}
