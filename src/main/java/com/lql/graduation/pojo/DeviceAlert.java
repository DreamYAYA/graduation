package com.lql.graduation.pojo;

import java.util.Date;

public class DeviceAlert {
    private String deviceAlertId;

    private String deviceAlertName;

    private String deviceAlertStatus;

    private String deviceInterfaceId;

    private String deviceAlertVal;

    private String deviceValue;

    private Integer deviceAlertSendmethod;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public String getDeviceAlertId() {
        return deviceAlertId;
    }

    public void setDeviceAlertId(String deviceAlertId) {
        this.deviceAlertId = deviceAlertId == null ? null : deviceAlertId.trim();
    }

    public String getDeviceAlertName() {
        return deviceAlertName;
    }

    public void setDeviceAlertName(String deviceAlertName) {
        this.deviceAlertName = deviceAlertName == null ? null : deviceAlertName.trim();
    }

    public String getDeviceAlertStatus() {
        return deviceAlertStatus;
    }

    public void setDeviceAlertStatus(String deviceAlertStatus) {
        this.deviceAlertStatus = deviceAlertStatus == null ? null : deviceAlertStatus.trim();
    }

    public String getDeviceInterfaceId() {
        return deviceInterfaceId;
    }

    public void setDeviceInterfaceId(String deviceInterfaceId) {
        this.deviceInterfaceId = deviceInterfaceId == null ? null : deviceInterfaceId.trim();
    }

    public String getDeviceAlertVal() {
        return deviceAlertVal;
    }

    public void setDeviceAlertVal(String deviceAlertVal) {
        this.deviceAlertVal = deviceAlertVal == null ? null : deviceAlertVal.trim();
    }

    public String getDeviceValue() {
        return deviceValue;
    }

    public void setDeviceValue(String deviceValue) {
        this.deviceValue = deviceValue == null ? null : deviceValue.trim();
    }

    public Integer getDeviceAlertSendmethod() {
        return deviceAlertSendmethod;
    }

    public void setDeviceAlertSendmethod(Integer deviceAlertSendmethod) {
        this.deviceAlertSendmethod = deviceAlertSendmethod;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}