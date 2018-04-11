package com.lql.graduation.mapper;

import com.lql.graduation.pojo.Device;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DeviceMapper {

    int deleteByPrimaryKey(String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer deviceId);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> selectByStatus();

    Device findDeviceByName(String deviceName);

    List<Device> selectDeviceByIsOnlineStatus(Integer status);

    int selectDeviceCount();
}