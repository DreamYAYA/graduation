package com.lql.graduation.util;

import java.io.Serializable;

public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;


    public ServerResponse(int status) {
        this.status = status;
    }

    public ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

   public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
   }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T>ServerResponse<T> createBySuccess(){

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T>ServerResponse<T> createBySuccessMessage(String message){

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),message);
    }

    public static <T>ServerResponse<T> createBySuccessMessage(T data){

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T>ServerResponse<T> createBySuccessMessage(String message,T data){

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),message,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }


}
