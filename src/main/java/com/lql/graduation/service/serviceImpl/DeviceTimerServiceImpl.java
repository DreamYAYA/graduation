package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.DeviceTimerMapper;
import com.lql.graduation.pojo.DeviceTimer;
import com.lql.graduation.service.DeviceTimerService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class DeviceTimerServiceImpl implements DeviceTimerService{

    @Autowired
    private DeviceTimerMapper deviceTimerMapper;

    /**
     *
     * 创建设备的定时器
     * @param deviceTimer
     */
    @Override
    public void createDeviceTimer(DeviceTimer deviceTimer) {

        String timerId = UidUtils.getUid();
        deviceTimer.setDeviceTimerId(timerId);

        //设置创建时间
        deviceTimer.setCreateTime(new Date());
        //设置默认启用
        deviceTimer.setDeviceTimerStatus(Constant.Status.OK_STATUS);

        deviceTimerMapper.insert(deviceTimer);

        //动态的添加定时的任务


    }

    public List<DeviceTimer> DeviceTimerListByStatus(Integer status){


        List<DeviceTimer> deviceTimerList = deviceTimerMapper.queryDeviceTimerByStatus(status);


        return deviceTimerList;
    }


    /**
     *
     * 更新定时器的接口
     * @param deviceTimer
     */
    @Override
    public void updateDeviceTimer(DeviceTimer deviceTimer) {

        deviceTimerMapper.updateByPrimaryKey(deviceTimer);

    }

    /**
     * 删除定时器
     * @param deviceTimer
     */
    @Override
    public void deleteDeviceTimer(DeviceTimer deviceTimer) {

        deviceTimer.setDeviceTimerStatus(Constant.Status.DELETE_STATUS);
        deviceTimerMapper.updateByPrimaryKeySelective(deviceTimer);

    }




}
