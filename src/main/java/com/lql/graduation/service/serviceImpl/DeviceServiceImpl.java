package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.DeviceMapper;
import com.lql.graduation.pojo.Device;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.ResponseCode;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;
import java.util.Date;
import java.util.UUID;

@Repository
public class DeviceServiceImpl implements DeviceService{

    @Autowired
   private DeviceMapper deviceMapper;

    /**
     *
     * 添加设备
     * @param device
     * @param Uid
     * @return
     */
    @Override
    public Integer addDevice(Device device,String Uid) {

        String deviceID = UidUtils.getUid();
        device.setDeviceId(deviceID);

        //初始化设备的各项参数
        device.setCrateBy(Uid);
        device.setCreatTime(new Date());
        device.setDeviceStatue(Constant.Device.DEVICE_STATUS_OK);
        //在线时长
        device.setDeviceTime(0);
        device.setDeviceIsonline(Constant.Device.DEVICE_OFF);

        try {
            deviceMapper.insert(device);
           return ResponseCode.SUCCESS.getCode();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseCode.ERROR.getCode();
        }
    }



}
