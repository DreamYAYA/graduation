package com.lql.graduation.controller;

import com.lql.graduation.mapper.DeviceMapper;
import com.lql.graduation.pojo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {


    @Autowired
    private DeviceMapper deviceMapper;


    @RequestMapping("/sayHello")
    @ResponseBody
    public String SatHello(){

      Device device = new Device();
      device.setDeviceId("1");
      device.setApikey("ssssssss");

        deviceMapper.insert(device);

        return "SayHello";
    }



}
