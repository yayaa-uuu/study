package com.wx.entity.fast;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wxli
 * @date 2021/8/5 19:16
 */
public class Test {
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"memberGroupId\":3001,\n" +
                "    \"memberGroups\":3001,\n" +
                "    \"memberFees\":{\n" +
                "        \"fees\":\"200.00\",\n" +
                "        \"groupId\":3001\n" +
                "    },\n" +
                "    \"member_info\":{\n" +
                "        \"batch_no\":\"481793231539\",\n" +
                "        \"park_code\":\"440103200001\",\n" +
                "        \"agent_id\":\"8010000000001\",\n" +
                "        \"operator\":\"4010000120101\",\n" +
                "        \"owner_name\":\"ww\",\n" +
                "        \"room_no\":\"1\",\n" +
                "        \"amount\":\"200.00\",\n" +
                "        \"remark\":\"\",\n" +
                "        \"member_level\":\"1\",\n" +
                "        \"tel_no\":\"15971447258\",\n" +
                "        \"mobile_veri\":\"0\",\n" +
                "        \"seat_num\":\"1\",\n" +
                "        \"region_code\":\"\",\n" +
                "        \"id_num\":\"421022199807016651\",\n" +
                "        \"start_date\":\"20210805\",\n" +
                "        \"end_date\":\"20210905\",\n" +
                "        \"member_type\":1\n" +
                "    },\n" +
                "    \"carid_info1\":{\n" +
                "        \"id\":3,\n" +
                "        \"car_num\":1,\n" +
                "        \"car_type\":\"1\",\n" +
                "        \"operate\":\"a01\",\n" +
                "        \"car_id\":\"鄂ARY000\",\n" +
                "        \"operator\":\"4010000120101\"\n" +
                "    },\n" +
                "    \"seat_info1\":{\n" +
                "        \"id\":\"\",\n" +
                "        \"role_name\":\"\",\n" +
                "        \"region_name\":\"\",\n" +
                "        \"region_code\":\"\",\n" +
                "        \"fees_roleid\":\"\",\n" +
                "        \"buy_num\":\"1\",\n" +
                "        \"start_date\":\"20210805\",\n" +
                "        \"end_date\":\"20210905\",\n" +
                "        \"owner_money\":200,\n" +
                "        \"car_type\":\"\",\n" +
                "        \"operate\":\"a01\",\n" +
                "        \"group_id\":3001,\n" +
                "        \"operator\":\"4010000120101\",\n" +
                "        \"amount\":\"200.00\",\n" +
                "        \"member_type\":1\n" +
                "    }\n" +

                "}";
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = JSON.parseObject(json, LinkedHashMap.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().contains("member_info")) {
                System.out.println(entry.getValue().toString());
                Map<String,Object> memberInfoMap = JSON.parseObject(entry.getValue().toString(), LinkedHashMap.class);
                Object o = memberInfoMap.get("park_code");
                System.out.println(o);
            }

        }
        System.out.println(map);
    }
}
