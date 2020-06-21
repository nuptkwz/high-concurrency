package com.practice.concurrency.highconcurrency.example.singleton;

import com.practice.concurrency.highconcurrency.annoation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * 单例模式---饿汉模式
 * 单例实例在类装载使用时进行创建(它能保证线程安全)
 * 通过静态代码块的方式
 * Date 2020/6/21 12:46
 * Created by kwz
 */
@Slf4j
@ThreadSafe
public class SingletonDemo6 {

    //饿汉模式缺点--如果类的构造方法中存在着过多的处理，会导致这个类加载的时候特别慢

    //私有构造函数
    private SingletonDemo6() {

    }

    //单例对象
    //当我们在写静态域或者静态代码块的时候，我们要注意顺序，如下面1和2顺序不能颠倒，
    //他们的执行结果会有所不同
    //1
    private static SingletonDemo6 instance = null;

    //2
    static {
        instance = new SingletonDemo6();
    }


    //静态工厂方法获取对象
    public static SingletonDemo6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }

}
