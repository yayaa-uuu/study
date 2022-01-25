//package com.wx.controller;
//
//import com.wx.cache.ManualCache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
///**
// * @author wxli
// * @date 2021/7/26 11:15
// */
//@RestController
//public class Test {
//    @Autowired
//    ManualCache manualCache;
//
//    @RequestMapping("test")
//    public void test2() {
//        String key = "hello";
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "设置缓存" + key);
//        manualCache.operator(key);
//    }
//
//    @RequestMapping("test3")
//    public void test3() {
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "\t" + manualCache.get("hello"));
//    }
//}
