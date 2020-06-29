package com.practice.concurrency.highconcurrency.example.synContainer;

import com.practice.concurrency.highconcurrency.annoation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * Description
 * ArrayList、HashSet、HashMap都不是线程安全的
 * Date 2020/6/21 21:42
 * Created by kwz
 */
@Slf4j
@NotThreadSafe
public class VectorDemo3 {

    //foreach
    //Exception in thread "main" java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> vector) {
        for (Integer curNum : vector) {
            if (curNum.equals(3)) {
                vector.remove(curNum);
            }
        }
    }

    //iterator
    //Exception in thread "main" java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> vector) {
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Integer curNum = iterator.next();
            if (curNum.equals(3)) {
                vector.remove(curNum);
            }
        }
    }

    //for
    private static void test3(Vector<Integer> vector) {
        for (int i=0;i<vector.size();i++){
            if (vector.get(i).equals(3)){
                vector.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }
}
