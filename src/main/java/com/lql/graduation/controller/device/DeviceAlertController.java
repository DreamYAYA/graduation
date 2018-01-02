package com.lql.graduation.controller.device;


import com.lql.graduation.pojo.DeviceAlert;
import com.lql.graduation.service.DeviceAlertService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/alert")
public class DeviceAlertController {


    @Autowired
    private DeviceAlertService deviceAlertService;


    @RequestMapping("/create")
    @ResponseBody
    public ServerResponse createDeviceAlert(DeviceAlert deviceAlert){

        deviceAlertService.createDeviceAlert(deviceAlert);

        return ServerResponse.createBySuccessMessage("数据接口创建成功");
    }

    @RequestMapping("/list")
    @ResponseBody
    public ServerResponse DeviceAlertList(){



        List<DeviceAlert> deviceAlertList = deviceAlertService.showDeviceAlertListByStatus(Constant.Status.OK_STATUS);



         return ServerResponse.createBySuccessMessage(deviceAlertList);
    }





}
