package com.lql.graduation.service.serviceImpl;

import com.google.common.primitives.Bytes;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

@Component
public class Consumer {

    @JmsListener(destination = "github-queue")
    public void receiveQueue(String text) {
        System.out.println(text);
    }

    @JmsListener(destination = "mytest.queue")

    public void receiveTopic(String text) {
        System.out.println(text);
    }

    @JmsListener(destination = "zh-topic")
    public void receiveQueue1(String text) {
        System.out.println("Consumer收到:"+text);
    }

    @JmsListener(destination = "zh-topic")
    @SendTo("mytest.queue")
    public String receiveQueue2(String message) throws UnsupportedEncodingException, JMSException {
        String[] bytesStr = message.split(",");
        byte[] a = new byte[bytesStr.length];
        for(int i=0;i<bytesStr.length;i++){
            a[i]=Byte.parseByte(bytesStr[i]);
        }
        System.out.println("Consumer2收到:"+ new String(a,"utf-8"));
        return "Consumer2收到!";
    }
}
