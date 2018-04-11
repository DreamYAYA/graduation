package com.lql.graduation.service.serviceImpl;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@Service
public class MessageSend {

    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;
    @Autowired
    private Topic topic;
    @Autowired
    private Queue queue;

    public void send(String msg) {

        this.jmsTemplate.convertAndSend(topic, msg);
    }
}
