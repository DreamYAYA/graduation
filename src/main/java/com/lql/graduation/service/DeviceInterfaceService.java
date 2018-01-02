package com.lql.graduation.service;


import com.lql.graduation.pojo.DeviceInterface;

import java.util.List;

public interface DeviceInterfaceService {
    List list(Integer status);

    void createDeviceInterface(DeviceInterface deviceInterface);
}
