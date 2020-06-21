package com.practice.concurrency.highconcurrency.threadlocal;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理高并发的对象
 * 1. 每个线程是独立的
 * 2. 登陆的时候会把每个用户的信息和请求的信息放入线程里面
 * 3. 而在以后取用户信息可以直接取这里取
 */
public class RequestHolder {


    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }

//    private static final ThreadLocal<Object> userHolder = new ThreadLocal<Object>();
//
//    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
//
//    public static void add(Object object) {
//        userHolder.set(object);
//    }
//
//    public static void add(HttpServletRequest request) {
//        requestHolder.set(request);
//    }
//
//    public static Object getCurrentUser() {
//        return userHolder.get();
//    }
//
//    public static HttpServletRequest getCurrentRequest() {
//        return requestHolder.get();
//    }
//
//    public static void remove() {
//        userHolder.remove();
//        requestHolder.remove();
//    }
}
