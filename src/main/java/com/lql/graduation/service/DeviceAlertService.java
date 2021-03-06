package com.lql.graduation.service;

import com.lql.graduation.pojo.DeviceAlert;
import com.lql.graduation.util.ServerResponse;

import java.util.List;

public interface DeviceAlertService {

    
    public Integer createDeviceAlert(DeviceAlert deviceAlert);


    List<DeviceAlert> showDeviceAlertListByStatus(Integer status);

    List<DeviceAlert> queryDeviceAlertListByInterId(String deviceInerfaceData);

    void checkAlertDataIsSuccesss(String interfaces, String message);

    ServerResponse getDeviceAlert(String id);

    ServerResponse updateDeviceAlert(DeviceAlert deviceAlert);
}


