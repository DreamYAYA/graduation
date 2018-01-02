package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.DeviceInterfaceDataMapper;
import com.lql.graduation.pojo.DeviceAlert;
import com.lql.graduation.pojo.DeviceInterfaceData;
import com.lql.graduation.service.DeviceAlertService;
import com.lql.graduation.service.DeviceInterfaceDataService;
import com.lql.graduation.service.DeviceInterfaceService;
import com.lql.graduation.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInterfaceDataServiceImpl implements DeviceInterfaceDataService{


    @Autowired
   private DeviceInterfaceDataMapper deviceInterfaceDataMapper;

    @Autowired
    private DeviceAlertService deviceAlertService;

    /**
     *
     * 插入数据接口的数据
     * @param deviceInterfaceData
     * @return
     */
    @Override
    public Integer insertInterfaceData(DeviceInterfaceData deviceInterfaceData) {

        deviceInterfaceDataMapper.insert(deviceInterfaceData);
        return ResponseCode.SUCCESS.getCode();
    }


    /**
     *
     * 检查插入的接口的数据是否达到报警的条件
     * @return
     */
    private Integer checkDataIsAlert(DeviceInterfaceData deviceInterfaceData){

        String deviceInerfaceData = deviceInterfaceData.getDeviceInerfaceData();

        /*
        *查询是否有数据对应接口的报警项
        *
        * */
        List<DeviceAlert> deviceAlertList = deviceAlertService.queryDeviceAlertListByInterId(deviceInterfaceData.getDeviceInterfaceId());
            //依次检查是否达到报警条件
        for (DeviceAlert deviceAlert: deviceAlertList) {

                chooseByRequirement(Double.parseDouble(deviceInerfaceData),deviceAlert.getDeviceAlertVal(),Double.parseDouble(deviceAlert.getDeviceValue()),deviceAlert);

        }
        return 1;
    }


    /**
     *
     *
     * @param deviceInerfaceData 设备传上来的数据
     * @param deviceAlertVal        报警的条件
     * @param deviceValue      报警条件的值
     */
    private void chooseByRequirement(Double deviceInerfaceData, String deviceAlertVal, Double deviceValue,DeviceAlert deviceAlert) {


        if(">".equals(deviceAlertVal)){

            if(deviceInerfaceData>deviceValue){
                sendMessageToAlert(deviceAlert);
            }

        }else if("<".equals(deviceAlertVal)){
            if(deviceInerfaceData<deviceValue){
                sendMessageToAlert(deviceAlert);
            }


        }else if("=".equals(deviceAlertVal)){
            if(deviceInerfaceData==deviceValue){
                sendMessageToAlert(deviceAlert);
            }

        }else if(">=".equals(deviceAlertVal)){
            if(deviceInerfaceData>=deviceValue){
                sendMessageToAlert(deviceAlert);
            }

        }else if("<=".equals(deviceAlertVal)){
            if(deviceInerfaceData<=deviceValue){
                sendMessageToAlert(deviceAlert);
            }

        }else if("!=".equals(deviceAlertVal)){
            if(deviceInerfaceData!=deviceValue){
                sendMessageToAlert(deviceAlert);
            }

        }


    }


    /**
     * 发送短信或者消息提醒用户达到报警值
     * @param deviceAlert
     */
    private void sendMessageToAlert(DeviceAlert deviceAlert) {
    }


}
