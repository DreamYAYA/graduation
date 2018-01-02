package com.lql.graduation.controller.device;


import com.lql.graduation.pojo.Device;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.ResponseCode;
import com.lql.graduation.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

       @RequestMapping("/creatDevice")
       @ResponseBody
       public ServerResponse addDevice(Device device,String Uid){

           Integer resultCode = deviceService.addDevice(device,Uid);

           if(resultCode== ResponseCode.SUCCESS.getCode()){
               return ServerResponse.createBySuccessMessage("添加设备成功");
           }else{
               return  ServerResponse.createByErrorMessage("添加设备失败");
           }

       }

        @RequestMapping("/deviceList")
        @ResponseBody
       public ServerResponse DeviceList(){
           List<Device> deviceList = deviceService.deviceList();
           return ServerResponse.createBySuccessMessage("查询成功",deviceList);
       }

    /**
     *
     * 查询在线的设备
     * @return
     */
    @RequestMapping("/queryOnlineDevice")
        @ResponseBody
       public ServerResponse DeviceLisOnline(){

           List<Device> onlineDevice = deviceService.onlineDeviceList(Constant.Device.DEVICE_ONLINE);
           if(onlineDevice!=null){
               return  ServerResponse.createBySuccessMessage("查询成功",onlineDevice);
           }
           return ServerResponse.createByErrorMessage("查询失败");
       }

    /**
     *
     * 发送控制消息
     * @param DeviceId
     * @param controllerMsg
     * @return
     */
       @RequestMapping("/sendMessage")
       @ResponseBody
       public ServerResponse SendMessageToDevice(String DeviceId,String controllerMsg){


           ServerResponse sendStatue = deviceService.sendMessageToDevice(DeviceId,controllerMsg);
            return sendStatue;
       }

}
