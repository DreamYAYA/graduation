package com.lql.graduation.mapper;

import com.lql.graduation.pojo.DeviceInterface;
import com.lql.graduation.pojo.DeviceInterfaceData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInterfaceDataMapper {
    int deleteByPrimaryKey(String dataId);

    int insert(DeviceInterfaceData record);

    int insertSelective(DeviceInterfaceData record);

    DeviceInterfaceData selectByPrimaryKey(String dataId);

    int updateByPrimaryKeySelective(DeviceInterfaceData record);

    int updateByPrimaryKey(DeviceInterfaceData record);

    List<DeviceInterfaceData> selectInterfaceData(DeviceInterface deviceInterface1);
}