package com.practice.concurrency.highconcurrency.test;

/**
 * Description
 * Date 2020/7/26 23:11
 * Created by kwz
 */
public class StringTest {

    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "aaa";
        String str3 = new String("aaa");

        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str2.equals(str3));
    }
}
