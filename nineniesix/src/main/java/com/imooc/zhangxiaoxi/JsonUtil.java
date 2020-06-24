/**
 * Copyright (C), 2020, XXX公司
 * FileName: JsonUtil
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.zhangxiaoxi;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-16 22:20
 */
public class JsonUtil {
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"5.jpg\": [\n" +
                "        \"甚至阿斯顿发了阿斯顿发生发的\",\n" +
                "        \"甚至阿斯顿发了阿斯顿发生发的\",\n" +
                "        \"大使馆啊发顺丰\"\n" +
                "    ],\n" +
                "    \"6.jpg\": [\n" +
                "        \"阿斯顿发生\",\n" +
                "        \"送达方式的发生\",\n" +
                "        \"撒飞洒发飞洒发给\"\n" +
                "    ],\n" +
                "    \"7.jpg\": [\n" +
                "        \"甚至阿斯顿发了阿斯顿发生发的\",\n" +
                "        \"撒旦法撒飞洒发生的法发顺丰\",\n" +
                "        \"甚至阿斯顿发了阿斯顿发生发的\",\n" +
                "        \"asdfsafasfasf\"\n" +
                "    ]\n" +
                "}";


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            Set<Map.Entry<String, Object>> set = map.entrySet();
            String ret = "";
            for (Map.Entry<String, Object> entry : set){
                Object o = entry.getValue();
                List<String> list = (ArrayList<String>) o;
                for (String temp : list){

                    ret += temp + System.lineSeparator();
                }

            }
            System.out.println(ret);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
