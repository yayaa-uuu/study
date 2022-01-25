//package com.wx.cache;
//
//import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
//import com.github.benmanes.caffeine.cache.CacheLoader;
//import com.github.benmanes.caffeine.cache.Caffeine;
//
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
///**
// * AsyncLoadingCache是继承自LoadingCache类的，
// * 异步加载使用Executor去调用方法并返回一个CompletableFuture。
// * 异步加载缓存使用了响应式编程模型。
// * <p>
// * 如果要以同步方式调用时，应提供CacheLoader。
// * 要以异步表示时，应该提供一个AsyncCacheLoader，并返回一个CompletableFuture。
// *
// * @author wxli
// * @date 2021/7/26 11:09
// */
//public class AsyncCache {
//    /**
//     * 异步加载
//     */
//    public void test() {
//        AsyncLoadingCache<String, Object> cache = Caffeine.newBuilder()
//                .maximumSize(100)
//                .expireAfterWrite(1, TimeUnit.MINUTES)
//                .buildAsync(new CacheLoader<String, Object>() {
//                    @Override
//                    public Object load(String key) throws Exception {
//                        return key;
//                    }
//                });
//        String key = "key";
//    }
//}
