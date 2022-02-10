package com.wx.redisson;

import com.wx.redisson.config.RedissonConfig;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * redisson    getLock（）  只会获取java对象。
 * 线程lock()成功之后    redis上会记录lock信息，
 * 在线程尝试加锁时，if leaseTime < executeTime  lock将在 leaseTime 后能够被重新创建，其他线程能够拿锁，在0-leaseTime加锁成功的线程可以重入
 *
 */
public class RedissonRLockTest {
    public static void main(String[] args) {
        RedissonConfig redissonConfig = new RedissonConfig();
        RedissonClient redissonClient = redissonConfig.redissonClient();
        //新建锁
        RLock lock = redissonClient.getLock("213");
        RLock lock2 = redissonClient.getLock("213");
        System.out.println(lock);
        System.out.println(lock2);
        //创建线程
        //竞争锁
//        try {
//            boolean b = lock.tryLock(0, 20, TimeUnit.SECONDS);
//            System.out.println(b);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean b = lock2.tryLock(0, 35, TimeUnit.SECONDS);
                    Thread.sleep(40);
                    System.out.println(b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        //模拟线程1获取锁之后释放，其他线程能否继续获取这把锁
    }
}
