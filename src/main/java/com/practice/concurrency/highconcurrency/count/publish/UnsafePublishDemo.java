package com.practice.concurrency.highconcurrency.count.publish;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Description
 * 这个类是线程不安全的，多线程场景下，我们不能保证是否其他线程访问到这个类的getStates
 * 修改了它的states引用
 * Date 2020/6/20 11:16
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
public class UnsafePublishDemo {

    private String[] states = {"a", "b", "c"};

    //通过类的非私有方法返回对象的引用
    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublishDemo unsafePublishDemo = new UnsafePublishDemo();
        log.info("{}", Arrays.toString(unsafePublishDemo.getStates()));

        unsafePublishDemo.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublishDemo.getStates()));

    }

}
