package com.practice.concurrency.highconcurrency.count.singleton;

import com.practice.concurrency.highconcurrency.annoation.Recommend;
import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * 枚举模式：  最安全的
 * 这是比较推荐的写法，它相比于我们的懒汉模式在安全性方面得到了保证，它相比于饿汉模式
 * 在实际调用的时候才做初始化
 * Date 2020/6/21 13:32
 * Created by kwz
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonDemo7 {

    //私有构造函数
    private SingletonDemo7() {

    }

    public static SingletonDemo7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonDemo7 singleton;

        //JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonDemo7();
        }

        public SingletonDemo7 getInstance() {
            return singleton;
        }
    }
}
