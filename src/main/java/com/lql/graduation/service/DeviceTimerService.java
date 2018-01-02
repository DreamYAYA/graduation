package com.lql.graduation.service;

import com.lql.graduation.pojo.DeviceTimer;

import java.util.List;

public interface DeviceTimerService {
    void createDeviceTimer(DeviceTimer deviceTimer);
    List<DeviceTimer> DeviceTimerListByStatus(Integer status);

    void updateDeviceTimer(DeviceTimer deviceTimer);

    void deleteDeviceTimer(DeviceTimer deviceTimer);

    DeviceTimer findDeviceTimerById(String timerId);
}
