package com.lql.graduation.service;

import com.lql.graduation.pojo.Device;

import java.util.List;

public interface DeviceService {


    Integer addDevice(Device device,String Uid);

    List<Device> deviceList();
}
