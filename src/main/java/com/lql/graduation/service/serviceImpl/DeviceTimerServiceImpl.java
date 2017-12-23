package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.common.ScheduleQuartz;
import com.lql.graduation.mapper.DeviceTimerMapper;
import com.lql.graduation.pojo.DeviceTimer;
import com.lql.graduation.pojo.Scheduler.TimerJob;
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

    @Autowired
    private ScheduleQuartz quartzManager;


    /**
     *
     * 创建设备的定时器
     * @param deviceTimer
     */
    @Override
    public void createDeviceTimer(DeviceTimer deviceTimer) {

        String timerId = UidUtils.getUid();
        String deviceTimerName = deviceTimer.getDeviceTimerName();
        deviceTimer.setDeviceTimerId(timerId);
        //设置创建时间
        deviceTimer.setCreateTime(new Date());
        //设置默认启用
        deviceTimer.setDeviceTimerStatus(Constant.Status.OK_STATUS);
        deviceTimerMapper.insert(deviceTimer);
        System.out.println("Cro 表达式:"+"0 "+deviceTimer.getDeviceTimerMiner()+" " +deviceTimer.getDeviceTiemrHour()+" " +"? "+"* "+deviceTimer.getDeviceTiemrWeek()+" ");
        //动态的添加定时的任务
        quartzManager.addJob(deviceTimerName, deviceTimerName, deviceTimerName, deviceTimerName, TimerJob.class, "0 "+deviceTimer.getDeviceTimerMiner()+" " +deviceTimer.getDeviceTiemrHour()+" " +"? "+"* "+deviceTimer.getDeviceTiemrWeek()+" ",timerId);

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

        String deviceTimerName = deviceTimer.getDeviceTimerName();
        deviceTimerMapper.updateByPrimaryKey(deviceTimer);
        quartzManager.modifyJobTime(deviceTimerName, deviceTimerName, deviceTimerName, deviceTimerName, "0 "+deviceTimer.getDeviceTimerMiner()+" " +deviceTimer.getDeviceTiemrHour()+" " +"? "+"* "+deviceTimer.getDeviceTiemrWeek()+" ");
    }

    /**
     * 删除定时器
     * @param deviceTimer
     */
    @Override
    public void deleteDeviceTimer(DeviceTimer deviceTimer) {

        String deviceTimerName = deviceTimer.getDeviceTimerName();
        deviceTimer.setDeviceTimerStatus(Constant.Status.DELETE_STATUS);
        deviceTimerMapper.updateByPrimaryKeySelective(deviceTimer);
        quartzManager.removeJob(deviceTimerName, deviceTimerName, deviceTimerName, deviceTimerName);
    }

    /**
     *
     * 根据ID查找定时器
     * @param timerId
     * @return
     */
    @Override
    public DeviceTimer findDeviceTimerById(String timerId) {

        DeviceTimer deviceTimer = deviceTimerMapper.selectByPrimaryKey(timerId);

        return deviceTimer;
    }


}
