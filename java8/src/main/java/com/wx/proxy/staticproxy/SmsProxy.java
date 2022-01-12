package com.wx.proxy.staticproxy;

import com.wx.proxy.SmsService;
import com.wx.proxy.SmsServiceImpl;

public class SmsProxy implements SmsService {
    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        System.out.println("before method send()");
        smsService.send(message);
        System.out.println("after method send()");
        return message;
    }

    public static void main(String[] args) {
        SmsProxy smsProxy=new SmsProxy(new SmsServiceImpl());
        smsProxy.send("hello world");
    }
}
