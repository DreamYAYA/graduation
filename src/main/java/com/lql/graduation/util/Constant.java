package com.lql.graduation.util;

public interface Constant {


public interface  Device{

    //设备可用状态 1.可用 0.不可用
    Integer DEVICE_STATUS_OK = 1;
    Integer DEVICE_STATUS_ERROR = 0;
    //设备是否在线
    Integer DEVICE_ONLINE = 1;
    Integer DEVICE_OFF = 0;
}

public interface aliDevice{

    //产品的ID名称
    String PRODUCT_KEY = "jqLf0X9GFja";
    String ACCESS_KEY = "LTAInnUiYQ9Ty7Yk";
    String ACCESS_SCREAT = "zevYTuE0hjyLp9oqUmmIJ6r7Hb2W3F";
    //默认的消息队列名称
    String QUEUE_DEFAULT_NAME = "aliyun-iot-jqLf0X9GFja";


}

//设备状态
public interface Status{

    //可用状态
    Integer OK_STATUS = 1;
  //弃用状态
    Integer ERROR_STATUS = 0;
  //删除状态
    Integer DELETE_STATUS = 3;


}




}
