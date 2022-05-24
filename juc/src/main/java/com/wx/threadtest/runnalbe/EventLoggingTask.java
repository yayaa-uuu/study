package com.wx.threadtest.runnalbe;


/**
 * 没有返回值的线程
 */
public class EventLoggingTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Message");
    }


}