package com.lql.graduation.pojo.Message;

import java.util.Date;

public class DataBean {

    private String status; //设备状态
    private String productKey;//产品标识
    private String deviceName;//设备标识
    private String time;//发送通知时间点
    private String lastTime;//状态变更时最后一次通信时间
    private String clientIp; //设备端公网出口IP


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }


    @Override
    public String toString() {
        return "DataBean{" +
                "status='" + status + '\'' +
                ", productKey='" + productKey + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", time=" + time +
                ", lastTime='" + lastTime + '\'' +
                ", clientIp='" + clientIp + '\'' +
                '}';
    }
}
