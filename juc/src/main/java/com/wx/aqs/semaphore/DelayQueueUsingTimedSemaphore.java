package com.wx.aqs.semaphore;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.util.concurrent.TimeUnit;

/**
 *
 * Apache Commons TimedSemaphore
 *
 * TimedSemaphore 允许多个许可作为一个简单得信号量，在给定时间段内。
 * 在此时间段之后时间重置并释放所有许可。
 * 利用TimedSemaphore 构建一个简单延迟队列。
 * @author wxli
 * @date 2021/7/15 22:40
 */
public class DelayQueueUsingTimedSemaphore {
    private TimedSemaphore semaphore;

    DelayQueueUsingTimedSemaphore(long period, int slotLimit) {
        semaphore = new TimedSemaphore(period, TimeUnit.SECONDS, slotLimit);
    }

    boolean tryAdd() {
        return semaphore.tryAcquire();
    }

    int availableSlots() {
        return semaphore.getAvailablePermits();
    }
}
