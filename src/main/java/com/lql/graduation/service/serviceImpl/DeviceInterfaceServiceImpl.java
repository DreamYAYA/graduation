package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.DeviceInterfaceMapper;
import com.lql.graduation.service.DeviceInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceInterfaceServiceImpl implements DeviceInterfaceService{


    @Autowired
    private DeviceInterfaceMapper deviceInterfaceMapper;

    public Integer insertMathInterface(){


       return null;
    }


}
