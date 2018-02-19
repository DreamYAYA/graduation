package com.lql.graduation.controller;

import com.lql.graduation.mapper.DeviceAlertMapper;
import com.lql.graduation.mapper.DeviceInterfaceMapper;
import com.lql.graduation.mapper.DeviceMapper;
import com.lql.graduation.pojo.Device;
import com.lql.graduation.pojo.DeviceAlert;
import com.lql.graduation.pojo.ModuleCount;
import com.lql.graduation.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {


    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceInterfaceMapper deviceInterfaceMapper;
    @Autowired
    private DeviceAlertMapper deviceAlertMapper;

    @RequestMapping("/sayHello")
    @ResponseBody
    public String SatHello(){

      Device device = new Device();
      device.setDeviceId("1");
      device.setApikey("ssssssss");

        deviceMapper.insert(device);

        return "SayHello";
    }
        @RequestMapping("/getModuleCount")
        @ResponseBody
        public ServerResponse getModuleCount(){
            ModuleCount moduleCount = new ModuleCount();
            try {
            int deviceCount = deviceMapper.selectDeviceCount();
            int deviceAlertCount = deviceAlertMapper.selectDeviceAlertCount();
            int deviceInterfaceCount = deviceInterfaceMapper.selectDeviceInterfaceCount();
                moduleCount.setAlertCount(deviceAlertCount);
                moduleCount.setDeviceCount(deviceCount);
                moduleCount.setInterfaceCount(deviceInterfaceCount);
                return ServerResponse.createBySuccessMessage(moduleCount);
            }catch (Exception e){

                return ServerResponse.createByErrorMessage(e.getMessage());
            }
        }

}
