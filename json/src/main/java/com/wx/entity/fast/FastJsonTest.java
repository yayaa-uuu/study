package com.wx.entity.fast;

import com.alibaba.fastjson.JSON;

/**
 *
 * https://www.runoob.com/w3cnote/fastjson-intro.html
 * @author wxli
 * @date 2021/7/13 23:04
 */
public class FastJsonTest {
    public static void main(String[] args) {

        // JSON 字符串
        String json = "{\"formId\":\"{$formId}\",\"link\":\"www.java3y.com\",\"text\":[{\"name\":\"java3y\",\"label\":\"3y\",\"value\":{\"value\":\"{$tureName}\",\"color\":\"\",\"emphasis\":\"\"}},{\"name\":\"java4y\",\"label\":\"3y\",\"value\":{\"value\":\"{$title}\",\"color\":\"\",\"emphasis\":\"\"}},{\"name\":\"java5y\",\"label\":\"5y\",\"value\":{\"value\":\"关注我的公众号，更多干货\",\"color\":\"#ff0040\",\"emphasis\":\"\"}}],\"yyyImg\":\"\",\"yyyAge\":\"\",\"pagepath\":\"\"}";

        // 使用JSON对象 将JSON字符串反序列化为JavaBean
        FastContentValue contentValue = JSON.parseObject(json, FastContentValue.class);
        System.out.println(contentValue);

        //转JSON
        String content=JSON.toJSONString(contentValue);
        System.out.println(content);


    }

}
