package com.lql.graduation.service.serviceImpl;

import com.aliyuncs.iot.model.v20170420.RegistDeviceResponse;
import com.lql.graduation.common.ali.aliDevice;
import com.lql.graduation.mapper.DeviceMapper;
import com.lql.graduation.pojo.Device;
import com.lql.graduation.pojo.Message.DataBean;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.ResponseCode;
import com.lql.graduation.util.TimeUtil;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceMapper deviceMapper;
    private final static String DEVICE_ONLINE = "online";
    private final static String DEVICE_OFFLINE = "offline";


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
            aliDevice aDevice = new aliDevice();
            RegistDeviceResponse resp = aDevice.CreateDeviceInIot(device.getDeviceName());
            if(resp.getSuccess()){
                device.setApikey(resp.getDeviceId());
                device.setApiscreat(resp.getDeviceSecret());
                deviceMapper.insert(device);

                return ResponseCode.SUCCESS.getCode();
                }else{
                throw new RuntimeException(resp.getErrorMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseCode.ERROR.getCode();
        }
    }


    /**
     *
     * 设备列表
     * @return
     */
    @Override
    public List<Device> deviceList() {

        List<Device> devices = deviceMapper.selectByStatus();
        return devices;
    }

    /**
     *
     * 修改设备的相关状态的信息
     * @param deviceData
     * @return
     */
    @Override
    public Integer updateDevice(DataBean deviceData) {
        String deviceName = deviceData.getDeviceName();
        Device device = deviceMapper.findDeviceByName(deviceName);

        if(DEVICE_ONLINE.equals(deviceData.getStatus())){
            //设备在线更改设备状态，更新设备的登录时间
            device.setDeviceStatue(Constant.Device.DEVICE_ONLINE);
            device.setUpdateTime(new Date());
        }else if(DEVICE_OFFLINE.equals(deviceData.getStatus())){
            //设备下线更改设备状态，计算设备的在线时间
            device.setDeviceStatue(Constant.Device.DEVICE_OFF);
            Date CurrentData = new Date();
            Date OldData = device.getUpdateTime();
            final long onlineTime = CurrentData.getTime() - OldData.getTime();
            Integer minuetime = TimeUtil.millTimeToMinue(onlineTime);
            device.setDeviceTime(device.getDeviceTime()+minuetime);
        }


        deviceMapper.updateByPrimaryKeySelective(device);


        return ResponseCode.SUCCESS.getCode();
    }

    /**
     * 根据Id查找设备
     *
     * @param deviceId
     * @return
     */
    @Override
    public Device findDeviceById(String deviceId) {

        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        return device;
    }


}
