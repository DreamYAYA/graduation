package com.lql.graduation.listeren;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.lql.graduation.common.ali.aliConfig;
import com.lql.graduation.pojo.DeviceInterfaceData;
import com.lql.graduation.pojo.Message.DataBean;
import com.lql.graduation.pojo.Message.DeviceData;
import com.lql.graduation.service.DeviceInterfaceDataService;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.JsonUtil;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Map;

/**
 *
 * 接收来自设备的消息
 */
public class ReciverMessageListeren implements ServletContextListener{

private static final String MESSAGE_TYPE_STATUS = "status";
private static final String MESSSAGE_TYPE_UPLOAD = "upload";

@Autowired
private DeviceService deviceService;
@Autowired
private DeviceInterfaceDataService deviceInterfaceDataService;


    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        aliConfig config = new aliConfig();
        final CloudQueue queue = config.getQue(Constant.aliDevice.QUEUE_DEFAULT_NAME);

        new Thread(){

            @Override
            public void run() {
                while(true){

                    Message popMsg = queue.popMessage(10); //长轮询等待时间为10秒
                    if (popMsg != null) {
                        String rowMessage = popMsg.getMessageBodyAsString();
                        try {
                            Map rowMap = JsonUtil.JsonToMap(rowMessage);
                            String messagetype = (String)rowMap.get("messagetype");//获取消息类型：1.是设备的发送的消息,2，是设备上下线的消息
                            String playload =(String)rowMap.get("payload"); //获取设备消息
                            System.out.println(messagetype+":"+playload);
                            if(MESSAGE_TYPE_STATUS.equals(messagetype)){
                                //是设备的通知数据(上下线的消息)
                                deviceService = getService(servletContextEvent,DeviceService.class);
                                DataBean deviceData = new DataBean();
                                deviceData = (DataBean)JsonUtil.JsonToObject(new String(Base64Utils.decodeFromString(playload),"utf-8"), DataBean.class);
                                deviceService.updateDevice(deviceData);//更新设备的状态

                            }else if(MESSSAGE_TYPE_UPLOAD.equals(messagetype)){
                                //是设备的发送的消息
                                deviceInterfaceDataService = getService(servletContextEvent,DeviceInterfaceDataService.class);
                                DeviceData deviceData = new DeviceData();
                                deviceData = (DeviceData)JsonUtil.JsonToObject(new String(Base64Utils.decodeFromString(playload),"utf-8"), DeviceData.class);

                                DeviceInterfaceData deviceInterfaceData = new DeviceInterfaceData();
                                deviceInterfaceData.setDeviceInterfaceId(deviceData.getInterfaces());
                                deviceInterfaceData.setDeviceInerfaceData(deviceData.getMessage());

                                deviceInterfaceDataService.insertInterfaceData(deviceInterfaceData);

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Json解析错误");
                        }
                        queue.deleteMessage(popMsg.getReceiptHandle()); //从队列中删除消息
                    } else {
                        System.out.println("Continuing");
                    }
                    try {
                        sleep(10000);
                        System.out.println("haha");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


    }

    private <T> T getService(ServletContextEvent sce,Class<T> clazz){
        T obj = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(clazz);

        return obj;
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }




}
