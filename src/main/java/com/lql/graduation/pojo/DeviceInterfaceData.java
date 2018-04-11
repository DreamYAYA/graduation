package com.lql.graduation.pojo;
import java.util.Date;
public class DeviceInterfaceData {
    private Integer dataId;

    private String deviceInterfaceId;

    private String deviceInerfaceData;

    private Date  dataTime;

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getDeviceInterfaceId() {
        return deviceInterfaceId;
    }

    public void setDeviceInterfaceId(String deviceInterfaceId) {
        this.deviceInterfaceId = deviceInterfaceId == null ? null : deviceInterfaceId.trim();
    }

    public String getDeviceInerfaceData() {
        return deviceInerfaceData;
    }

    public void setDeviceInerfaceData(String deviceInerfaceData) {
        this.deviceInerfaceData = deviceInerfaceData == null ? null : deviceInerfaceData.trim();
    }
}