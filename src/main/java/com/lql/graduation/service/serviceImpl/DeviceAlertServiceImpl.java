package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.DeviceAlertMapper;
import com.lql.graduation.pojo.DeviceAlert;
import com.lql.graduation.service.DeviceAlertService;
import com.lql.graduation.util.ResponseCode;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceAlertServiceImpl implements DeviceAlertService{

        @Autowired
        private DeviceAlertMapper deviceAlertMapper;

        public Integer createDeviceAlert(DeviceAlert deviceAlert){

            String AlertId = UidUtils.getUid();
            deviceAlert.setDeviceAlertId(AlertId);
            //创建时间
            deviceAlert.setCreateTime(new Date());
            deviceAlert.setUpdateTime(new Date());
            deviceAlertMapper.insert(deviceAlert);

            return ResponseCode.SUCCESS.getCode();
        }

    @Override
    public List<DeviceAlert> showDeviceAlertListByStatus(Integer status) {

        List<DeviceAlert> deviceAlertList = deviceAlertMapper.queryDeviceAlertListByStatus(status);
            return deviceAlertList;
    }

    /**
     *
     * 根据接口ID查询报警列表
     * @param deviceInerfaceData
     * @return
     */
    @Override
    public List<DeviceAlert> queryDeviceAlertListByInterId(String deviceInerfaceData) {



        List<DeviceAlert> deviceAlertList = deviceAlertMapper.queryDeviceAlertListByInterId(deviceInerfaceData);

        return deviceAlertList;
    }


}
