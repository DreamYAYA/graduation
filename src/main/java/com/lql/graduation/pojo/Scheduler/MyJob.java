package com.lql.graduation.pojo.Scheduler;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyJob {

    public void doSomething(){
        System.out.println(new Date() + ": job 2 doing something...");
    }


}
