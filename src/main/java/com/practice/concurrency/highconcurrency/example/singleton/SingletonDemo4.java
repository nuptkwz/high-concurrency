package com.practice.concurrency.highconcurrency.example.singleton;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * 单例模式---懒汉模式  -->>双重同步锁代理模式
 * Date 2020/6/21 12:46
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
public class SingletonDemo4 {

    //私有构造函数
    private SingletonDemo4() {

    }

    /**
     * 在单线程模式下没问题的，但是在多线程情况下就会不安全了：
     * 1、memory = allocate() 分配对象的内存空间
     * 2、ctorInstance()初始化对象
     * 3、instance = memory 设置instance指向放分配的内存
     * 因为可能会发生指令重排
     * <p>
     * jvm和cpu优化，发生了指令重排，执行顺序可能为：
     * 1、memory = allocate() 分配对象的内存空间
     * 3、instance = memory 设置instance指向放分配的内存
     * 2、ctorInstance()初始化对象
     */

    //单例对象
    private static SingletonDemo4 instance = null;

    /**
     * 比如说两个线程，线程A、B同时调用这个getInstance方法
     * A执行到了下面A的位置，而B执行到了下面B的位置
     * 如果按照上面说的指令重排的1,3,2顺序 A执行了3，而B线程来了之后
     * 发现A线程已经有值了（但是它还没执行2对其进行初始化操作），然后直接返回了
     * 就会出问题了
     */

    //静态工厂方法获取对象
    public static SingletonDemo4 getInstance() {
        if (instance == null) {//双重检测机制 DCL     //B
            synchronized (SingletonDemo4.class) {  //同步锁
                if (instance == null) {
                    instance = new SingletonDemo4();  //A -3
                }
            }
        }
        return instance;
    }

}
