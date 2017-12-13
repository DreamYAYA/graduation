package com.lql.graduation.mapper;

import com.lql.graduation.pojo.DeviceAlert;

import java.util.List;

public interface DeviceAlertMapper {
    int deleteByPrimaryKey(String deviceAlertId);

    int insert(DeviceAlert record);

    int insertSelective(DeviceAlert record);

    DeviceAlert selectByPrimaryKey(String deviceAlertId);

    int updateByPrimaryKeySelective(DeviceAlert record);

    int updateByPrimaryKey(DeviceAlert record);

    List<DeviceAlert> queryDeviceAlertListByInterId(String deviceInerfaceData);

    List<DeviceAlert> queryDeviceAlertListByStatus(Integer status);
}