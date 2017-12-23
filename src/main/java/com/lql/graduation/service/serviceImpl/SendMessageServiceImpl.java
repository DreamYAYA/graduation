package com.lql.graduation.service.serviceImpl;

import com.aliyuncs.iot.model.v20170420.PubResponse;
import com.lql.graduation.common.ali.aliDevice;
import com.lql.graduation.mapper.DeviceMapper;
import com.lql.graduation.pojo.Device;
import com.lql.graduation.service.SendMessageService;
import com.lql.graduation.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private DeviceMapper deviceMapper;

    /**
     *
     * 向设备发送消息
     * @param deviceName
     * @param msg
     * @return
     */
    @Override
    public Boolean sendDeviceMessage(String deviceName,String msg,String topicName) {

        aliDevice device = new aliDevice();
        //pub消息到对应的topic /jqLf0X9GFja/${deviceName}/update
        String topic = "/"+Constant.aliDevice.PRODUCT_KEY+"/"+deviceName+"/"+topicName;
        PubResponse pubResponse = device.pubTest(Constant.aliDevice.PRODUCT_KEY, topic, msg);
        Boolean isSuccess = pubResponse.getSuccess();

        return isSuccess;
    }







}
