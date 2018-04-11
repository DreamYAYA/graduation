package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.pojo.DeviceInterfaceData;
import com.lql.graduation.pojo.Message.DeviceData;
import com.lql.graduation.pojo.Message.NewDeviceData;
import com.lql.graduation.service.DeviceInterfaceDataService;
import com.lql.graduation.util.JsonUtil;
import com.lql.graduation.util.StringUtil;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;

/**
 *
 * 接收系统发送的消息
 */
@Service
public class DataMessageReceverService {

    @Autowired
    private DeviceInterfaceDataService deviceInterfaceDataService;
    @Autowired
    private DeviceAlertServiceImpl deviceAlertService;

    @JmsListener(destination = "device.datamessage")
    public void receiveDataMessage(String text) throws Exception {
                    /*
                    *
                    * 规定传输的数据的格式要有要现在规定如下
                    * data=
{
    "m":"123"(或offline),//发送的消息
    "pk":"1",//设备名称
    "i":"" //发送消息的接口
}
                          @TODO    插入数据的同时去监测相应的接口是否存在有效的报警列表，有且符合相关的报警条件则发送消息进行提醒
                    * */
        text = StringUtil.byteToString(text);
        DeviceData deviceData = new DeviceData();
        deviceData = (DeviceData) JsonUtil.JsonToObjects(text, NewDeviceData.class);
        DeviceInterfaceData deviceInterfaceData = new DeviceInterfaceData();
        deviceInterfaceData.setDeviceInterfaceId(deviceData.getInterfaces());
        deviceInterfaceData.setDeviceInerfaceData(deviceData.getMessage());
        deviceInterfaceDataService.insertInterfaceData(deviceInterfaceData);
        //检测数据是否达到报警条件
        deviceAlertService.checkAlertDataIsSuccesss( deviceData.getInterfaces(),deviceData.getMessage());
    }



    }

