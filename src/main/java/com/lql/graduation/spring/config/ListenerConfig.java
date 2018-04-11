package com.lql.graduation.spring.config;

import com.lql.graduation.listeren.ReciverMessageListeren;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


public class ListenerConfig {



    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean slrBean = new ServletListenerRegistrationBean();
        slrBean.setListener(new ReciverMessageListeren());
        return slrBean;
    }

}
