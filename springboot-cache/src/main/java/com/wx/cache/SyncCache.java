//package com.wx.cache;
//
//import com.github.benmanes.caffeine.cache.CacheLoader;
//import com.github.benmanes.caffeine.cache.Caffeine;
//import com.github.benmanes.caffeine.cache.LoadingCache;
//import com.github.benmanes.caffeine.cache.RemovalCause;
//import lombok.SneakyThrows;
//import org.junit.Test;
//
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Function;
//
///**
// * https://www.baeldung.com/java-caching-caffeine
// * <p>
// * https://www.cnblogs.com/rickiyang/p/11074158.html
// * <p>
// * 构造Cache时候，build方法传入一个CacheLoader实现类。实现load方法，通过key加载value。
// * 同步加载，get()方法会直接初始化值，
// *
// * @author wxli
// * @date 2021/7/26 11:01
// */
//
//public class SyncCache {
//    @SneakyThrows
//    @Test
//    public void test2() {
//        LoadingCache<String, Object> cache = Caffeine.newBuilder()
//                //基于大小驱逐，可被撑大。
//                .maximumSize(2)
//                //基于时间驱逐
//                .expireAfterWrite(10, TimeUnit.SECONDS)
////            .expireAfterAccess(1, TimeUnit.SECONDS)
//                //监听移除
//                .removalListener((String key, Object value, RemovalCause cause) ->
//                        System.out.printf("removed %s (%s)%n", key, cause))
//                .build(new CacheLoader<String, Object>() {
//                    @Override
//                    public Object load(String key) throws Exception {
//                        System.out.println("loading " + key);
//                        return key + "value";
//                    }
//                });
//        //同一个过期策略之下，在缓存没有过期时，或缓存没有设置时，
//        //只会调用一次load方法。
//        cache.get("100");
//        cache.get("111");
//        cache.get("112");
//        cache.get("113");
//        long l = cache.estimatedSize();
//        System.out.println(l);
//        System.out.println(cache.getIfPresent("100"));
//
//    }
//
//    /**
//     * 缓存大小还是会被撑大，缓存的驱逐是异步的，
//     * 获取缓存大小可以调用cleanUp方法。
//     */
//    @SneakyThrows
//    @Test
//    public void test3() {
//        LoadingCache<String, Object> cache = Caffeine.newBuilder()
//                //根据权重删除
//                .maximumWeight(2)
//                //获取缓存大小
//                .weigher((k, v) -> 1)
//                //监听移除
//                .removalListener((String key, Object value, RemovalCause cause) ->
//                        System.out.printf("removed %s (%s)%n", key, cause))
//                .build(key -> {
//                    System.out.println("loading " + key);
//                    return key;
//                });
//        //同一个过期策略之下，在缓存没有过期时，或缓存没有设置时，
//        //只会调用一次load方法。
//        cache.get("100");
//        cache.get("111");
//        cache.invalidateAll();
//        cache.get("112");
//        cache.get("113");
//        long l = cache.estimatedSize();
//        System.out.println(l);
//        System.out.println(cache.getIfPresent("100"));
//
//    }
//
//
//    /**
//     * https://www.baeldung.com/java-weakhashmap
//     * Java引用介绍
//     *
//     * 我们可以配置我们的缓存以允许缓存键和/或值的垃圾收集。
//     * 为此，我们需要为键和值配置WeakReference 的使用，
//     * 并且我们可以配置SoftReference 仅用于值的垃圾收集。
//     */
//    @SneakyThrows
//    @Test
//    public void test4() {
//        LoadingCache<String, Object> cache = Caffeine.newBuilder()
//                .expireAfterWrite(10, TimeUnit.SECONDS)
//                .weakKeys()
//                .weakValues()
//
////                .softValues()
//                //监听移除
//                .removalListener((String key, Object value, RemovalCause cause) ->
//                        System.out.printf("removed %s (%s)%n", key, cause))
//                .build(key -> {
//                    System.out.println("loading " + key);
//                    return key;
//                });
//        //同一个过期策略之下，在缓存没有过期时，或缓存没有设置时，
//        //只会调用一次load方法。
//        cache.get("100");
//        cache.get("111");
////        cache.invalidateAll();
//        cache.get("112");
//        cache.get("113");
//        long l = cache.estimatedSize();
//        System.out.println(l);
//        System.out.println(cache.getIfPresent("100"));
//
//    }
//}
