package com.lql.graduation.controller.device;


import com.lql.graduation.pojo.DeviceTimer;
import com.lql.graduation.service.DeviceTimerService;
import com.lql.graduation.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("deviceTimer")
public class DeviceTimerController {

    @Autowired
    private DeviceTimerService deviceTimerService;


    //设备定时器
    @RequestMapping("create")
    @ResponseBody
    public ServerResponse createDeviceTimer(DeviceTimer deviceTimer){


        deviceTimerService.createDeviceTimer(deviceTimer);
        return ServerResponse.createBySuccessMessage("创建定时器成功!");
    }


        @RequestMapping("query")
        @ResponseBody
        private ServerResponse queryDeviceTimer(){

        List<DeviceTimer> deviceTimers = deviceTimerService.DeviceTimerListByStatus(null);
        return ServerResponse.createBySuccessMessage(deviceTimers);

    }
        @RequestMapping("updata")
        @ResponseBody
        private ServerResponse updataDeviceTimer(DeviceTimer deviceTimer){


            deviceTimerService.updateDeviceTimer(deviceTimer);
            return ServerResponse.createBySuccessMessage("更新定时器成功");
        }

        @RequestMapping("delete")
        @ResponseBody
        public ServerResponse deleteDeviceTimer(DeviceTimer deviceTimer){

            deviceTimerService.deleteDeviceTimer(deviceTimer);
            return ServerResponse.createBySuccessMessage("删除成功");
        }

}
