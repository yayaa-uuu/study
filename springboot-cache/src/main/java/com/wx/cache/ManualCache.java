package com.wx.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 在每次get key的时候指定一个同步的函数，如果key不存在就调用这个函数生成一个值。
 *
 * @author wxli
 * @date 2021/7/26 10:59
 */
@Component
public class ManualCache {
    Cache<String, Object> cache = Caffeine.newBuilder()
            //在最后一次写入缓存后开始计时，在指定的时间后过期。
//            .expireAfterWrite(5, TimeUnit.SECONDS)
            //访问后过期，如果一直访问，那么会刷新时间，如果不访问，5秒之后就自动过期
            //在最后一次访问或者写入后开始计时，在指定的时间后过期。
            // 假如一直有请求访问该key，那么这个缓存将一直不会过期。
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .maximumSize(10)
            .build();

    /**
     * 手动加载
     *
     * @param key
     * @return
     */
    public Object operator(String key) {

        //如果一个key不存在，那么会进入指定的函数生成value
        Object value = cache.get(key, t -> setValue(key).apply(key));
        cache.put("hello", value);
        return value;
    }

    public Object get(String key) {
        //判断是否存在如果不存返回null
        Object ifPresent = cache.getIfPresent(key);
        //移除一个key
//        cache.invalidate(key);
        return ifPresent;
    }

    public Function<String, Object> setValue(String key) {
        return t -> key + "value";
    }
}
