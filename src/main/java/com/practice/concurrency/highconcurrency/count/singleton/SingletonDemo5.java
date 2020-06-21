package com.practice.concurrency.highconcurrency.count.singleton;

import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * 单例模式---懒汉模式  -->>双重同步锁代理模式
 * 限制它产生指令重排，用volatile关键字
 * Date 2020/6/21 12:46
 * Created by kwz
 */
@Slf4j
@ThreadSafe
public class SingletonDemo5 {

    //私有构造函数
    private SingletonDemo5() {

    }

    /**
     * 1、memory = allocate() 分配对象的内存空间
     * 2、ctorInstance()初始化对象
     * 3、instance = memory 设置instance指向放分配的内存
     */

    //单例对象  volatile + 双重检测机制  ->   禁止指令重排
    private static volatile SingletonDemo5 instance = null;

    //静态工厂方法获取对象
    public static SingletonDemo5 getInstance() {
        if (instance == null) {//双重检测机制 DCL     //B
            synchronized (SingletonDemo5.class) {  //同步锁
                if (instance == null) {
                    instance = new SingletonDemo5();  //A -3
                }
            }
        }
        return instance;
    }


}
