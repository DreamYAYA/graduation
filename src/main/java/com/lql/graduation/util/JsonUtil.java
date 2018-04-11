package com.lql.graduation.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lql.graduation.pojo.Message.DataBean;
import com.lql.graduation.pojo.Message.DeviceData;
import com.lql.graduation.pojo.Message.NewDeviceData;
import com.lql.graduation.pojo.Vo.DeviceOnlineVo;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class JsonUtil {


    public static Object  JsonToObject(String jsonStr,Class obj) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.readValue(jsonStr, obj);
        DeviceOnlineVo deviceOnlineVo = (DeviceOnlineVo) o;
        DataBean dataBean = new DataBean();
        dataBean.setProductKey(deviceOnlineVo.getPk());
        dataBean.setStatus(deviceOnlineVo.getS());
        dataBean.setTime(LocalDateTime.now().toString());
        return dataBean;
    }
    public static Object  JsonToObjects(String jsonStr,Class obj) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.readValue(jsonStr, obj);
        NewDeviceData newDeviceData = (NewDeviceData) o;
        DeviceData deviceData = new DeviceData();
        deviceData.setInterfaces(newDeviceData.getIt());
        deviceData.setMessage(newDeviceData.getM());
        deviceData.setDeviceName(newDeviceData.getPk());
        deviceData.setTime(new Date().toString());
        return deviceData;
    }

    public static Map JsonToMap(String jsonStr) throws IOException {

        Map resMap  = null;
        ObjectMapper mapper = new ObjectMapper();
        resMap = mapper.readValue(jsonStr, Map.class);
        return resMap;
    }

}
