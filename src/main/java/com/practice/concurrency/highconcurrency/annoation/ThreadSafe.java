package com.practice.concurrency.highconcurrency.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description
 * 用来标注线程安全的类和写法
 * Date 2020/6/18 0:45
 * Created by kwz
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)//注解存在的范围
public @interface ThreadSafe {

    String value() default "";
}
