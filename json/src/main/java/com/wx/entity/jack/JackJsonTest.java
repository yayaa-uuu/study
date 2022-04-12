package com.wx.entity.jack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


/**
 * https://juejin.cn/post/6844904166809157639
 * <p>
 * 重要类，objectMapper     JsonNode
 * ObjectNode      extends     JsonNode
 *
 * @author wxli
 * @date 2021/7/12 13:48
 */
public class JackJsonTest {
    //    public static void main(String[] args) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "{\\\"IsInExemption\\\":0,\\\"accesstime\\\":1626068444,\\\"actualDiscount\\\":0,\\\"amounttotal\\\":0,\\\"cash_pay\\\":0,\\\"chargeSchemID\\\":1,\\\"couponDescription\\\":\\\"\\\",\\\"couponID\\\":0,\\\"cut_amount\\\":0,\\\"dikouamount\\\":0,\\\"fee\\\":0,\\\"isRepeat\\\":0,\\\"lastOrderID\\\":20,\\\"nAmountCap\\\":0,\\\"plateNumber\\\":\\\"苏AAAAAA\\\",\\\"record\\\":{\\\"detail\\\":\\\"\\\",\\\"detailInfo\\\":\\\"\\\",\\\"record_info\\\":[],\\\"region_id\\\":\\\"\\\",\\\"region_info\\\":[],\\\"scumulative\\\":\\\"\\\",\\\"scumulativeInfo\\\":\\\"\\\"},\\\"record_id\\\":0,\\\"regionID\\\":0,\\\"timeLength\\\":0,\\\"time_cut\\\":0}";
//        String s = json.replaceAll("\\\\", "");
//        System.out.println(s);
//        try {
//            Map map = objectMapper.readValue(s, Map.class);
//            System.out.println(map);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//
//    }
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"info\": [\n" +
                "        {\n" +
                "            \"id\": 11,\n" +
                "            \"people\": {\n" +
                "                \"teather\": {\n" +
                "                    \"name\": \"cjq\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(json);
            ObjectNode root = (ObjectNode) jsonNode;

            JsonNode info = jsonNode.get("info");
            ArrayNode array= (ArrayNode) info;
            System.out.println(array);


            JsonNode id = array.findValue("id");
            JsonNode people = array.findValue("people");

            JsonNode value = array.get(0);

            ObjectNode newValue= (ObjectNode) value;
            ObjectNode jsonNodes = newValue.deepCopy();

            jsonNodes.remove("id");
            jsonNodes.remove("people");

//            jsonNodes.set("id",id);
            jsonNodes.set("people",people);

            array.add(jsonNodes);
            System.out.println(root);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

}
