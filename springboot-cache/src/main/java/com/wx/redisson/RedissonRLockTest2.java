package com.wx.redisson;

import com.wx.redisson.config.RedissonConfig;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * redisson    getLock（）  只会获取java对象。
 * 线程lock()成功之后    redis上会记录lock信息，
 * 在线程尝试加锁时，if leaseTime < executeTime  lock将在 leaseTime 后能够被重新创建，其他线程能够拿锁，在0-leaseTime加锁成功的线程可以重入
 */
public class RedissonRLockTest2 {
    public static void main(String[] args) {
        RedissonConfig redissonConfig = new RedissonConfig();
        RedissonClient redissonClient = redissonConfig.redissonClient();
        System.out.println(System.currentTimeMillis());
        //新建锁
        RLock lock = redissonClient.getLock("213");
        try {
            boolean b = lock.tryLock(3, TimeUnit.SECONDS);
            if (b) {
                Thread.sleep(50000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(System.currentTimeMillis());
    }
}
