package com.lql.graduation.service;

import com.lql.graduation.pojo.DeviceAlert;

import java.util.List;

public interface DeviceAlertService {

    
    public Integer createDeviceAlert(DeviceAlert deviceAlert);


    List<DeviceAlert> showDeviceAlertListByStatus(Integer status);

    List<DeviceAlert> queryDeviceAlertListByInterId(String deviceInerfaceData);
}


