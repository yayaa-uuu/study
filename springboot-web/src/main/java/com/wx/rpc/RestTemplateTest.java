package com.wx.rpc;

import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        // 企业id
        String corpid = "wx15687db123";
        // 应用对应的secretID
        String corpsecret = "31ux_biu6-456fgdf45578QHE-daZT32gzoI";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret + "";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);
    }
}
