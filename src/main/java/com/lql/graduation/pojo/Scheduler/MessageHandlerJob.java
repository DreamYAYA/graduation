package com.lql.graduation.pojo.Scheduler;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.model.Message;
import com.lql.graduation.common.ali.aliConfig;
import com.lql.graduation.pojo.DeviceInterfaceData;
import com.lql.graduation.pojo.Message.DataBean;
import com.lql.graduation.pojo.Message.DeviceData;
import com.lql.graduation.service.DeviceAlertService;
import com.lql.graduation.service.DeviceInterfaceDataService;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.service.serviceImpl.DeviceServiceImpl;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.JsonUtil;
import com.lql.graduation.util.RedisUtil;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;


/**
 * 定时任务，定时维护在线设备，检查在线的设备
 */
@Component
public class MessageHandlerJob {
    @Autowired
    private RedisUtil redisUtil;
    private final static String ONLINE_DEVICE = "onlineDevice";

    @Autowired
    private DeviceServiceImpl deviceService;

    public void doSomething() {
        System.out.println("MessageHandlerJob.................................");
        Map<String, String> deviceMap = redisUtil.hmgetAll(ONLINE_DEVICE);
        Set<String> deviceKeySet = deviceMap.keySet();
        for (String key:deviceKeySet) {
            String stringDate = deviceMap.get(key);
            LocalDateTime onlineTime = LocalDateTime.parse(stringDate);
            LocalDateTime endTime = LocalDateTime.now();
            Duration between = Duration.between(onlineTime, endTime);
            Long betweenTime = between.toMinutes();
            if(betweenTime>1){
                redisUtil.removeHset(ONLINE_DEVICE,key);
                //并且修改设备的状态
                DataBean dataBean = new DataBean("of",key);
                deviceService.updateDevice(dataBean);
                System.out.println("设备"+key+"被清除");
            }
        }
    }

}
