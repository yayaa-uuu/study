package com.wx.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * If-Modified-Since
 *
 */
@RestController
public class ConditionalGETMethodController {

    @GetMapping("/ifModifiedSince")
    public String ifModifiedSince(){
        return "123";
    }
}
