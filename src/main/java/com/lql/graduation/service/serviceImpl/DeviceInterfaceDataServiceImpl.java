package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.DeviceInterfaceDataMapper;
import com.lql.graduation.pojo.DeviceInterfaceData;
import com.lql.graduation.service.DeviceInterfaceDataService;
import com.lql.graduation.service.DeviceInterfaceService;
import com.lql.graduation.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceInterfaceDataServiceImpl implements DeviceInterfaceDataService{


    @Autowired
   private DeviceInterfaceDataMapper deviceInterfaceDataMapper;


    @Override
    public Integer insertInterfaceData(DeviceInterfaceData deviceInterfaceData) {

        deviceInterfaceDataMapper.insert(deviceInterfaceData);
        return ResponseCode.SUCCESS.getCode();
    }


}
