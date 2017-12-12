package com.lql.graduation.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {


    public static Object  JsonToObject(String jsonStr,Class obj) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.readValue(jsonStr, obj);
        return o;
    }

    public static Map JsonToMap(String jsonStr) throws IOException {

        Map resMap  = null;
        ObjectMapper mapper = new ObjectMapper();
        resMap = mapper.readValue(jsonStr, Map.class);
        return resMap;
    }

}
