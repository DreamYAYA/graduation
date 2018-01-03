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
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.JsonUtil;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.Date;
import java.util.Map;


/**
 * 定时任务，定时的接收来之设备的消息，并调用相应的类进行处理
 */
@Component
public class MessageHandlerJob {

    private static final String MESSAGE_TYPE_STATUS = "status";
    private static final String MESSSAGE_TYPE_UPLOAD = "upload";

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceInterfaceDataService deviceInterfaceDataService;
    @Autowired
    private DeviceAlertService deviceAlertService;


    public void doSomething() {
        System.out.println("MessageHandlerJob.................................");
        aliConfig config = new aliConfig();
        final CloudQueue queue = config.getQue(Constant.aliDevice.QUEUE_DEFAULT_NAME);
        Message popMsg = queue.popMessage(2); //长轮询等待时间为2秒
        if (popMsg != null) {
            String rowMessage = popMsg.getMessageBodyAsString();
            try {
                Map rowMap = JsonUtil.JsonToMap(rowMessage);
                String messagetype = (String) rowMap.get("messagetype");//获取消息类型：1.是设备的发送的消息,2，是设备上下线的消息
                String playload = (String) rowMap.get("payload"); //获取设备消息
                System.out.println(messagetype + ":" + playload);
                if (MESSAGE_TYPE_STATUS.equals(messagetype)) {
                    //是设备的通知数据(上下线的消息)
                    DataBean deviceData = new DataBean();
                    deviceData = (DataBean) JsonUtil.JsonToObject(new String(Base64Utils.decodeFromString(playload), "utf-8"), DataBean.class);
                    deviceService.updateDevice(deviceData);//更新设备的状态

                } else if (MESSSAGE_TYPE_UPLOAD.equals(messagetype)) {
                    //是设备的发送的消息
                    /*
                    *
                    * 规定传输的数据的格式要有要求
                    * 现在规定如下
                    * data=
{
    "message":"123"(或offline),//发送的消息
    "deviceName":"xxxxxxxxxx",//设备标识
    "time":"2017-10-11 10:11:12.234", //发送消息的时间
    "interface":"" //发送消息的接口
    "clientIp":"" //设备端公网出口IP
}
                          @TODO    插入数据的同时去监测相应的接口是否存在有效的报警列表，有且符合相关的报警条件则发送消息进行提醒
                    * */
                    DeviceData deviceData = new DeviceData();
                    deviceData = (DeviceData) JsonUtil.JsonToObject(new String(Base64Utils.decodeFromString(playload), "utf-8"), DeviceData.class);

                    DeviceInterfaceData deviceInterfaceData = new DeviceInterfaceData();
                    deviceInterfaceData.setDataId(UidUtils.getUid());
                    deviceInterfaceData.setDeviceInterfaceId(deviceData.getInterfaces());
                    deviceInterfaceData.setDeviceInerfaceData(deviceData.getMessage());
                     deviceInterfaceDataService.insertInterfaceData(deviceInterfaceData);
                    //检测数据是否达到报警条件
                    deviceAlertService.checkAlertDataIsSuccesss( deviceData.getInterfaces(),deviceData.getMessage());
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Json解析错误");
            }
            queue.deleteMessage(popMsg.getReceiptHandle()); //从队列中删除消息
        } else {
            System.out.println("Continuing");
        }
    }

}
