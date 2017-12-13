package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.DeviceInterfaceMapper;
import com.lql.graduation.pojo.DeviceInterface;
import com.lql.graduation.service.DeviceInterfaceService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceInterfaceServiceImpl implements DeviceInterfaceService{


    @Autowired
    private DeviceInterfaceMapper deviceInterfaceMapper;

    public Integer insertMathInterface(){


       return null;
    }


    /**
     *
     * 数据接口的列表
     * @param status
     * @return
     */
    @Override
    public List list(Integer status) {
       List<DeviceInterface> deviceInterfaceList = deviceInterfaceMapper.selectByStatus(status);
        return deviceInterfaceList;
    }

    /**
     *
     * 创建数据接口
     * @param deviceInterface
     */
    @Override
    public void createDeviceInterface(DeviceInterface deviceInterface) {


        deviceInterface.setDeviceInterfaceId(UidUtils.getUid());
        deviceInterface.setDeviceInterfaceStatus(Constant.Status.OK_STATUS+"");
        deviceInterface.setCreateTime(new Date());
        deviceInterfaceMapper.insert(deviceInterface);
    }


}
