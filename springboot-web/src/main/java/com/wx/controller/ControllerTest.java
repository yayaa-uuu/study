package com.wx.controller;

import com.wx.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wxli
 * @date 2021/7/7 21:01
 */
@Controller
public class ControllerTest {
    //控制器
    @Autowired
    Rating rating;
    @RequestMapping("test")
    public void handlerMappingTest() {
        System.out.println(rating.toString());
    }

}
