package com.wx.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class RLockTest {
    @Autowired
    private RedissonClient redissonClient;


    /**
     * 使用redisson使用对订单号加锁保证同一时刻只有一个线程执行业务逻辑
     * <p>
     * 测试目的，
     * 1.redisson能否正常完成工作。
     * 2.前一个线程执行，设置 lock 释放后 10秒删除lock，后一个线程
     *
     * @param orderId 订单号
     */
    @RequestMapping("lock/{orderId}")
    public void lockTest(@PathVariable String orderId) {
        RLock lock = redissonClient.getLock(orderId);
        lock.lock();
        try {
            log.info("开始业务逻辑");
            Thread.sleep(5000);
            log.info("任务执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * RLock  trylock()
     * leaseTime租用时间，最早获取锁的线程重入可刷新时间
     * 其他线程在租用时间内无法获取改lock锁
     *
     * 如租用时间到期之后，业务还未处理完毕，redisson不会自动续期
     *
     * @param orderId 订单号
     */
    @RequestMapping("tryLock/{orderId}")
    public void tryLockTest(@PathVariable String orderId) {
        RLock lock = redissonClient.getLock(orderId);
        try {
            if (lock.tryLock(0, 5, TimeUnit.SECONDS)) {
                log.info("开始业务逻辑");
                Thread.sleep(20000);
                log.info("任务执行结束");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("lock release");

            }
        }
    }


}
