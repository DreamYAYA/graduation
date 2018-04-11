package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.pojo.Message.DataBean;
import com.lql.graduation.pojo.Vo.DeviceOnlineVo;
import com.lql.graduation.service.DeviceService;
import com.lql.graduation.util.JsonUtil;
import com.lql.graduation.util.RedisUtil;
import com.lql.graduation.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 接收设备上下线消息
 */
@Component
public class OnlineMessageService {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private RedisUtil redisUtil;
    private final static String ONLINE_DEVICE = "onlineDevice";
    @JmsListener(destination = "device.message")
    public void receiveQueue1(String text) {
        Map rowMap = null;
        try {
            text = StringUtil.byteToString(text);
            DataBean deviceData = new DataBean();
            deviceData = (DataBean) JsonUtil.JsonToObject(text, DeviceOnlineVo.class);
            deviceService.updateDevice(deviceData);//更新设备的状态

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *接收设备的心跳消息
     * @param pk 设备ID
     */
    @JmsListener(destination = "device.message.heart")
    public void reciverHeart(String pk) throws UnsupportedEncodingException {
        pk = StringUtil.byteToString(pk);
        redisUtil.hmset(ONLINE_DEVICE,pk, LocalDateTime.now().toString());
    }



}
