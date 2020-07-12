package com.practice.concurrency.highconcurrency.example.singleton;

/**
 * Description
 * Date 2020/7/12 17:34
 * Created by kwz
 */
public class SingleTest {

    public static void main(String[] args) {
        //编译时错误：构造函数 SingletonDemo1() 是不可见的
//        SingletonDemo1 singletonDemo1 = new SingletonDemo1();

        SingletonDemo1 instance = SingletonDemo1.getInstance();
    }
}
