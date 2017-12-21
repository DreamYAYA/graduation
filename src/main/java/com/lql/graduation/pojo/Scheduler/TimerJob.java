package com.lql.graduation.pojo.Scheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 *
 *定时器类执行的任务类
 */
public class TimerJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        String strData = dataMap.getString("love");


        System.out.println(new Date() + ": job 1 doing something...");
    }




}
