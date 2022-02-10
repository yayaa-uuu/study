package com.wx.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RedissonConfig {
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setPassword("yy123")
                .setAddress("redis://" + "119.91.96.216" + ":" + 6379)
                .setDatabase(8);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
