package com.lql.graduation.service;


import com.lql.graduation.pojo.DeviceInterface;
import com.lql.graduation.util.ServerResponse;

import java.util.List;

public interface DeviceInterfaceService {
    List list(Integer status);

    void createDeviceInterface(DeviceInterface deviceInterface);

    ServerResponse getDeviceInterfaceById(Integer id);

    ServerResponse updateInterface(DeviceInterface deviceInterface);

    ServerResponse getInterfaceValue(DeviceInterface deviceInterface);
}
