package com.lql.graduation.controller.device;


import com.lql.graduation.pojo.Device;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.util.ResponseCode;
import com.lql.graduation.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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



}
