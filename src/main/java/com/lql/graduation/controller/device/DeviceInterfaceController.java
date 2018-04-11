package com.lql.graduation.controller.device;

import com.lql.graduation.pojo.DeviceInterface;
import com.lql.graduation.service.DeviceInterfaceService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/dataInterface")
public class DeviceInterfaceController {

    @Autowired
    private DeviceInterfaceService deviceInterfaceService;

    @RequestMapping("/list")
    @ResponseBody
    public ServerResponse dataInterfaceList(){

        List<DeviceInterface> deviceInterfaceList = deviceInterfaceService.list(Constant.Status.OK_STATUS);
        return ServerResponse.createBySuccessMessage(deviceInterfaceList);

    }

    /**
     *
     * 创建数据接口
     * @param deviceInterface
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public ServerResponse createDeviceInterface(DeviceInterface deviceInterface){

        deviceInterfaceService.createDeviceInterface(deviceInterface);
        return ServerResponse.createBySuccessMessage("创建数据接口成功");

    }

    /**
     * 根据ID获取接口数据
     * @param id
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public ServerResponse getDeviceInterfaceById(Integer id){

        ServerResponse serverResponse = deviceInterfaceService.getDeviceInterfaceById(id);
            return serverResponse;
    }

        @RequestMapping("/update")
         @ResponseBody
        public ServerResponse updataInterface(DeviceInterface deviceInterface){
            deviceInterface.setUpdateTimer(new Date());
            ServerResponse serverResponse = deviceInterfaceService.updateInterface(deviceInterface);
            return serverResponse;
        }

        @RequestMapping("/getValue")
        @ResponseBody
        public ServerResponse getIntefaceValue(DeviceInterface deviceInterface){

            ServerResponse serverResponse = deviceInterfaceService.getInterfaceValue(deviceInterface);
            return serverResponse;
        }



}
