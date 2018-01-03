package com.lql.graduation.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mail {

@Autowired
private   JavaMailSender mailSender;


    public   void sendSimplMail(String target, String subject, String content){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("836957413@qq.com");//发送者.
        message.setTo(target);//接收者.
        message.setSubject(subject);//邮件主题.
        message.setText(content);//邮件内容.
        mailSender.send(message);//发送邮件

    }






}
