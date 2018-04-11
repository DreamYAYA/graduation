package com.lql.graduation.spring.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private static final String clientid = "server";
    private static final String HOST = "tcp://127.0.0.1:1883";

    @Bean
    public MqttConnectOptions setMqttConnectOptions(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName("admin");
        options.setPassword("admin".toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);


        return options;
    }
    @Bean
    public MqttMessage setMqttMessage(){
        MqttMessage message = new MqttMessage();
        //设置服务质量
        message.setQos(2);
        //设置是否在服务器中保存消息体
        message.setRetained(true);

        return message;
    }
    @Bean
    public MqttClient setMqttClient(MqttConnectOptions options) throws MqttException {

        MqttClient mqttClient = new MqttClient(HOST,clientid,new MemoryPersistence());
        // 连接
        mqttClient.connect(options);
        return mqttClient;
    }





}
