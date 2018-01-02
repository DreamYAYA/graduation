package com.lql.graduation.pojo;

import java.util.Date;

public class DeviceTimer {

    private String deviceTimerId;


    private String devDeviceId;


    private String deviceId;


    private String deviceTimerName;


    private String deviceTimerTode;


    private Integer deviceTiemrWeek;


    private Integer deviceTiemrHour;


    private Integer deviceTimerMiner;


    private String deviceTimerMes;

    private Integer deviceTimerStatus;


    private String createBy;


    private Date createTime;


    private String updateBy;


    private Date updateTimer;

    public String getDeviceTimerId() {
        return deviceTimerId;
    }


    public void setDeviceTimerId(String deviceTimerId) {
        this.deviceTimerId = deviceTimerId == null ? null : deviceTimerId.trim();
    }


    public String getDevDeviceId() {
        return devDeviceId;
    }


    public void setDevDeviceId(String devDeviceId) {
        this.devDeviceId = devDeviceId == null ? null : devDeviceId.trim();
    }


    public String getDeviceId() {
        return deviceId;
    }


    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }


    public String getDeviceTimerName() {
        return deviceTimerName;
    }


    public void setDeviceTimerName(String deviceTimerName) {
        this.deviceTimerName = deviceTimerName == null ? null : deviceTimerName.trim();
    }


    public String getDeviceTimerTode() {
        return deviceTimerTode;
    }


    public void setDeviceTimerTode(String deviceTimerTode) {
        this.deviceTimerTode = deviceTimerTode == null ? null : deviceTimerTode.trim();
    }


    public Integer getDeviceTiemrWeek() {
        return deviceTiemrWeek;
    }


    public void setDeviceTiemrWeek(Integer deviceTiemrWeek) {
        this.deviceTiemrWeek = deviceTiemrWeek;
    }


    public Integer getDeviceTiemrHour() {
        return deviceTiemrHour;
    }


    public void setDeviceTiemrHour(Integer deviceTiemrHour) {
        this.deviceTiemrHour = deviceTiemrHour;
    }


    public Integer getDeviceTimerMiner() {
        return deviceTimerMiner;
    }


    public void setDeviceTimerMiner(Integer deviceTimerMiner) {
        this.deviceTimerMiner = deviceTimerMiner;
    }


    public String getDeviceTimerMes() {
        return deviceTimerMes;
    }


    public void setDeviceTimerMes(String deviceTimerMes) {
        this.deviceTimerMes = deviceTimerMes == null ? null : deviceTimerMes.trim();
    }


    public Integer getDeviceTimerStatus() {
        return deviceTimerStatus;
    }


    public void setDeviceTimerStatus(Integer deviceTimerStatus) {
        this.deviceTimerStatus = deviceTimerStatus;
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


    public Date getUpdateTimer() {
        return updateTimer;
    }


    public void setUpdateTimer(Date updateTimer) {
        this.updateTimer = updateTimer;
    }
}