package com.wx.threadtest.runnalbe;

import lombok.extern.slf4j.Slf4j;

/**
 * 没有返回值的线程
 */
@Slf4j
public class EventLoggingTask implements Runnable {
    @Override
    public void run() {
        log.info("Message");
    }


}