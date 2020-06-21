package com.practice.concurrency.highconcurrency.example.publish;

import com.practice.concurrency.highconcurrency.annoation.NotRecommend;
import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 * Date 2020/6/20 11:24
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class EscapeDemo {

    private int thisCanBeEscape = 0;

    public EscapeDemo() {
        new InnerClass();
    }

    public class InnerClass {
        public InnerClass() {
            log.info("{}", EscapeDemo.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new EscapeDemo();
    }
}
