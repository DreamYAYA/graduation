package com.lql.graduation.service.serviceImpl;

import com.aliyuncs.iot.model.v20170420.PubResponse;
import com.lql.graduation.common.ali.aliDevice;
import com.lql.graduation.mapper.DeviceMapper;
import com.lql.graduation.service.SendMessageService;
import com.lql.graduation.util.Constant;
import org.apache.activemq.command.ActiveMQTopic;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private org.eclipse.paho.client.mqttv3.MqttClient MqttClient;
    @Autowired
    private MqttMessage message;
    /**
     * 向设备发送消息
     * @param deviceId
     * @param msg
     * @return
     */
    @Override
    public Boolean sendDeviceMessage(String deviceId,String msg,String topicName) {

        boolean isSuccess = false;
        //pub消息到对应的topic /device/${deviceId}/get
        try {
            MqttTopic topic =  MqttClient.getTopic("device/"+deviceId+"/get");
            message.setPayload(msg.getBytes());
            MqttDeliveryToken token = topic.publish(message);
            // 发布
            token.waitForCompletion();
            isSuccess = true;
        }catch (Exception e){
        e.printStackTrace();
        }
        return isSuccess;
    }







}
