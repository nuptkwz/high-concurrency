package com.practice.concurrency.highconcurrency.example.synContainer;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

/**
 * Description
 * ArrayList、HashSet、HashMap都不是线程安全的
 * Date 2020/6/21 21:42
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
public class VectorDemo1 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
