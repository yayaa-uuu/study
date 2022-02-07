package com.wx.controller;


import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultPromise;

/**
 * @author wxli
 * @date 2021/7/7 21:01
 */
public class MonoControllerTest {

    public static void main(String[] args) {
        DefaultEventExecutor eventExecutors = new DefaultEventExecutor();
        DefaultPromise defaultPromise = new DefaultPromise(eventExecutors);
        eventExecutors.execute(()-> defaultPromise.setSuccess("hello world"));
//        eventExecutors.execute(()-> defaultPromise.setFailure(new RuntimeException("error")));
        defaultPromise.addListener(future -> System.out.println(defaultPromise.get()));

    }

}
