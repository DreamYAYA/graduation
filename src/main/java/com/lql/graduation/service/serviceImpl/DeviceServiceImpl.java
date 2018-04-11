package com.lql.graduation.service.serviceImpl;

import com.aliyuncs.iot.model.v20170420.RegistDeviceResponse;
import com.lql.graduation.common.ali.aliDevice;
import com.lql.graduation.mapper.DeviceMapper;
import com.lql.graduation.pojo.Device;
import com.lql.graduation.pojo.Message.DataBean;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.service.SendMessageService;
import com.lql.graduation.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private SendMessageService sendMessageService;
    private final static String DEVICE_ONLINE = "o";
    private final static String DEVICE_OFFLINE = "of";
    private final static String ONLINE_DEVICE = "onlineDevice";
    @Autowired
    private RedisUtil redisUtil;

    /**
     *
     * 添加设备
     * @param device
     * @param Uid
     * @return
     */
    public Integer addDevice(Device device,String Uid) {
        try{
            //初始化设备的各项参数
            device.setCrateBy(Uid);
            device.setCreatTime(new Date());
            device.setDeviceStatue(Constant.Device.DEVICE_STATUS_OK);
            //在线时长
            device.setDeviceTime(0);
            device.setDeviceIsonline(Constant.Device.DEVICE_OFF);
            deviceMapper.insert(device);
            return ResponseCode.SUCCESS.getCode();
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
    public Integer updateDevice(DataBean deviceData) {
        String deviceName = deviceData.getDeviceName();
        Device device = deviceMapper.selectByPrimaryKey(Integer.parseInt(deviceData.getProductKey()));
        if(device==null){
                System.out.println(deviceData.getProductKey()+"设备不存在!");
        }
        else if(DEVICE_ONLINE.equals(deviceData.getStatus())){
            //设备在线更改设备状态，更新设备的登录时间
            device.setDeviceIsonline(Constant.Device.DEVICE_ONLINE);
            device.setUpdateTime(new Date());
            //将设备信息写入Redis中进行在线设备的维护
            redisUtil.hmset(ONLINE_DEVICE,deviceData.getProductKey(),deviceData.getTime());

        }else if(DEVICE_OFFLINE.equals(deviceData.getStatus())){
            //设备下线更改设备状态，计算设备的在线时间
            device.setDeviceIsonline(Constant.Device.DEVICE_OFF);
            Date CurrentData = new Date();
            Date OldData = device.getUpdateTime();
            final long onlineTime = CurrentData.getTime() - OldData.getTime();
            Integer minuetime = TimeUtil.millTimeToMinue(onlineTime);
            device.setDeviceTime(device.getDeviceTime()+minuetime);
            //设备下线时删除Redis中维护的设备
            redisUtil.removeHset(ONLINE_DEVICE,deviceData.getProductKey());
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
    public Device findDeviceById(String deviceId) {

        Device device = deviceMapper.selectByPrimaryKey(Integer.parseInt(deviceId));
        return device;
    }

    /**
     * 查询在线的设备
     * @return
     */
    public List<Device> onlineDeviceList(Integer status) {

            List<Device> onlineDeviceList = deviceMapper.selectDeviceByIsOnlineStatus(status);

        return onlineDeviceList;
    }

    /**
     *
     * 向设备发送消息
     * @param deviceId
     * @param controllerMsg
     * @return
     */
    public ServerResponse sendMessageToDevice(String deviceId, String controllerMsg) {
        Boolean success = sendMessageService.sendDeviceMessage(deviceId, controllerMsg, "get");
        if(success){
            return ServerResponse.createBySuccessMessage("消息发送成功");
        }
        return ServerResponse.createByErrorMessage("发送消息失败");
    }

    /**
     *
     * 反转设备状态
     * @param deviceId
     * @return
     */
    @Override
    public ServerResponse reversStatus(String deviceId) {
        try {
            Device device = deviceMapper.selectByPrimaryKey(Integer.parseInt(deviceId));
            device.setDeviceStatue(device.getDeviceStatue()^1);
            deviceMapper.updateByPrimaryKeySelective(device);
            return ServerResponse.createBySuccess();
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }


    }


}
