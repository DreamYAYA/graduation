package com.lql.graduation.pojo;

public class DeviceInterfaceData {
    private String dataId;

    private String deviceInterfaceId;

    private String deviceInerfaceData;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
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