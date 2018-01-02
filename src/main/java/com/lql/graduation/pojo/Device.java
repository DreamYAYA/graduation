package com.lql.graduation.pojo;

import java.util.Date;

public class Device {
    private String deviceId;

    private String userId;

    private String deviceName;

    private String apikey;

    private String apiscreat;

    private String deviceIntroduce;

    private String deviceDescription;

    private String deviceIspublic;

    private Integer deviceIsonline;

    private Integer deviceTime;

    private Integer deviceStatue;

    private String crateBy;

    private Date creatTime;

    private String updateBy;

    private Date updateTime;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey == null ? null : apikey.trim();
    }

    public String getApiscreat() {
        return apiscreat;
    }

    public void setApiscreat(String apiscreat) {
        this.apiscreat = apiscreat == null ? null : apiscreat.trim();
    }

    public String getDeviceIntroduce() {
        return deviceIntroduce;
    }

    public void setDeviceIntroduce(String deviceIntroduce) {
        this.deviceIntroduce = deviceIntroduce == null ? null : deviceIntroduce.trim();
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription == null ? null : deviceDescription.trim();
    }

    public String getDeviceIspublic() {
        return deviceIspublic;
    }

    public void setDeviceIspublic(String deviceIspublic) {
        this.deviceIspublic = deviceIspublic == null ? null : deviceIspublic.trim();
    }

    public Integer getDeviceIsonline() {
        return deviceIsonline;
    }

    public void setDeviceIsonline(Integer deviceIsonline) {
        this.deviceIsonline = deviceIsonline;
    }

    public Integer getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Integer deviceTime) {
        this.deviceTime = deviceTime;
    }

    public Integer getDeviceStatue() {
        return deviceStatue;
    }

    public void setDeviceStatue(Integer deviceStatue) {
        this.deviceStatue = deviceStatue;
    }

    public String getCrateBy() {
        return crateBy;
    }

    public void setCrateBy(String crateBy) {
        this.crateBy = crateBy == null ? null : crateBy.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
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