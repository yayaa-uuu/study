package com.wx.aqs.semaphore;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author wxli
 * @date 2021/7/15 22:28
 */
public class SemaphoreTest {
    /**
     * 我们将首先尝试达到限制并检查下一次登录尝试是否会被阻止：
     */
    @Test
    public void givenLoginQueue_whenReachLimit_thenBlocked() {
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore loginQue = new LoginQueueUsingSemaphore(slots);
        IntStream.range(0, slots).forEach(user -> executorService.execute(loginQue::tryLogin));
        executorService.shutdown();
        assertEquals(0, loginQue.availableSlots());
        assertFalse(loginQue.tryLogin());
    }

    /**
     * 接下来，我们将查看注销后是否有可用的插槽：
     */
    @Test
    public void givenLoginQueue_whenLogout_thenSlotsAvailable(){
        int slots = 10;
        ExecutorService executorService=Executors.newFixedThreadPool(slots);
        LoginQueueUsingSemaphore loginQue=new LoginQueueUsingSemaphore(slots);
        IntStream.range(0,slots)
                .forEach(user->executorService.execute(loginQue::tryLogin));
        executorService.shutdown();
        assertEquals(0,loginQue.availableSlots());
        loginQue.logout();

        int i = loginQue.availableSlots();
        System.out.println(i);
        assertTrue(i >0);
        assertTrue(loginQue.tryLogin());
    }


    /**
     * 当我们使用以一秒为时间段的延迟队列并且在一秒内使用所有插槽后，应该没有可用的：
     * 但是在睡眠一段时间后，信号量应该重置并释放许可：
     * @throws InterruptedException
     */
    @Test
    public void givenDelayQueue_whenTimePass_thenSlotsAvailable() throws InterruptedException {
        int slots = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        DelayQueueUsingTimedSemaphore delayQueue = new DelayQueueUsingTimedSemaphore(1, slots);
        IntStream.range(0, slots)
                .forEach(user -> executorService.execute(delayQueue::tryAdd));
        executorService.shutdown();

        assertEquals(0, delayQueue.availableSlots());
        Thread.sleep(1000);
        assertTrue(delayQueue.availableSlots() > 0);
        assertTrue(delayQueue.tryAdd());
    }
}
