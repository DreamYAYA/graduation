package com.lql.graduation.pojo.Scheduler;

import com.lql.graduation.mapper.DeviceMapper;
import com.lql.graduation.pojo.Device;
import com.lql.graduation.pojo.DeviceTimer;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.service.DeviceTimerService;
import com.lql.graduation.service.SendMessageService;
import com.lql.graduation.util.LogUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 *定时器类执行的任务类
 */
@Component
public class TimerJob implements Job {

    @Autowired
    private DeviceTimerService deviceTimerService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private SendMessageService sendMessageService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String timerId = dataMap.getString("timerId");

        DeviceTimer deviceTimer = deviceTimerService.findDeviceTimerById(timerId);
        //获取发送的命令
        String deviceTimerMes = deviceTimer.getDeviceTimerMes();
        //获取发送的设备的对象
        String deviceId = deviceTimer.getDeviceId();

        Device device = deviceService.findDeviceById(deviceId);

        if(device==null){

            throw new RuntimeException("获取设备失败");

        }
        Boolean success = sendMessageService.sendDeviceMessage(device.getDeviceName(), deviceTimerMes, "get");

        if(success){
            LogUtil.info("消息发送成功!");
        }else{
            LogUtil.info(device.getDeviceId()+": "+"消息发送成功!");
        }
        System.out.println(timerId);
    }




}
