package com.lql.graduation.service;

import com.lql.graduation.pojo.Device;
import com.lql.graduation.pojo.Message.DataBean;
import com.lql.graduation.util.ServerResponse;

import java.util.List;

public interface DeviceService {

        //添加设备
    Integer addDevice(Device device,String Uid);
    //查询设备列表
    List<Device> deviceList();
        //更新设备信息
    Integer updateDevice(DataBean deviceData);
    //根据ID查找指定设备
    Device findDeviceById(String deviceId);
    //查询在线设备
    List<Device> onlineDeviceList(Integer status);
        //发送消息至设备
    ServerResponse sendMessageToDevice(String deviceId, String controllerMsg);


    ServerResponse reversStatus(String deviceId);
}
