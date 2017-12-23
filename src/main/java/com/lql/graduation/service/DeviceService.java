package com.lql.graduation.service;

import com.lql.graduation.pojo.Device;
import com.lql.graduation.pojo.Message.DataBean;

import java.util.List;

public interface DeviceService {


    Integer addDevice(Device device,String Uid);

    List<Device> deviceList();

    Integer updateDevice(DataBean deviceData);

    Device findDeviceById(String deviceId);
}
