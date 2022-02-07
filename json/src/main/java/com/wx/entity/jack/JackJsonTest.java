package com.wx.entity.jack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * https://juejin.cn/post/6844904166809157639
 *
 * 重要类，objectMapper     JsonNode
 *         ObjectNode      extends     JsonNode
 *
 * @author wxli
 * @date 2021/7/12 13:48
 */
public class JackJsonTest {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\\\"IsInExemption\\\":0,\\\"accesstime\\\":1626068444,\\\"actualDiscount\\\":0,\\\"amounttotal\\\":0,\\\"cash_pay\\\":0,\\\"chargeSchemID\\\":1,\\\"couponDescription\\\":\\\"\\\",\\\"couponID\\\":0,\\\"cut_amount\\\":0,\\\"dikouamount\\\":0,\\\"fee\\\":0,\\\"isRepeat\\\":0,\\\"lastOrderID\\\":20,\\\"nAmountCap\\\":0,\\\"plateNumber\\\":\\\"苏AAAAAA\\\",\\\"record\\\":{\\\"detail\\\":\\\"\\\",\\\"detailInfo\\\":\\\"\\\",\\\"record_info\\\":[],\\\"region_id\\\":\\\"\\\",\\\"region_info\\\":[],\\\"scumulative\\\":\\\"\\\",\\\"scumulativeInfo\\\":\\\"\\\"},\\\"record_id\\\":0,\\\"regionID\\\":0,\\\"timeLength\\\":0,\\\"time_cut\\\":0}";
        String s = json.replaceAll("\\\\", "");
        System.out.println(s);
        try {
            Map map = objectMapper.readValue(s, Map.class);
            System.out.println(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

}
