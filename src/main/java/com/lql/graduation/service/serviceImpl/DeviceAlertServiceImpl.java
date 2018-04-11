package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.common.Mail;
import com.lql.graduation.mapper.DeviceAlertMapper;
import com.lql.graduation.pojo.DeviceAlert;
import com.lql.graduation.service.DeviceAlertService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.ResponseCode;
import com.lql.graduation.util.ServerResponse;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceAlertServiceImpl implements DeviceAlertService{

        @Autowired
        private DeviceAlertMapper deviceAlertMapper;
        @Autowired
        private Mail mail;

        public Integer createDeviceAlert(DeviceAlert deviceAlert){

            String AlertId = UidUtils.getUid();
            deviceAlert.setDeviceAlertId(AlertId);
            deviceAlert.setDeviceAlertStatus(Constant.Status.OK_STATUS+"");
            //创建时间
            deviceAlert.setCreateTime(new Date());
            deviceAlert.setUpdateTime(new Date());
            deviceAlertMapper.insert(deviceAlert);

            return ResponseCode.SUCCESS.getCode();
        }

    @Override
    public List<DeviceAlert> showDeviceAlertListByStatus(Integer status) {

        List<DeviceAlert> deviceAlertList = deviceAlertMapper.queryDeviceAlertListByStatus(status);
            return deviceAlertList;
    }

    /**
     *
     * 根据接口ID查询报警列表
     * @param deviceInerfaceData
     * @return
     */
    @Override
    public List<DeviceAlert> queryDeviceAlertListByInterId(String deviceInerfaceData) {



        List<DeviceAlert> deviceAlertList = deviceAlertMapper.queryDeviceAlertListByInterId(deviceInerfaceData);

        return deviceAlertList;
    }


    /**
     * 监测数据是否达到数据接口的报警的条件
     * @param DeviceAlertInterfaceId 数据接口ID
     * @param interfaceData   相应接口的数据
     *                        @TODO 动态的获取邮箱
     */
    @Override
    public void checkAlertDataIsSuccesss(String DeviceAlertInterfaceId, String interfaceData) {

        List<DeviceAlert> deviceAlertList = deviceAlertMapper.queryDeviceAlertByInterfaceId(DeviceAlertInterfaceId);
        //监测是否达到报警条件
        for (DeviceAlert deviceAlert:deviceAlertList) {
                if("1".equals(deviceAlert.getDeviceAlertVal())){
                    if(StringToInteger(interfaceData)>StringToInteger(deviceAlert.getDeviceValue())){
                        SendMails(Constant.MailTest.testAccount,deviceAlert.getDeviceInterfaceId(),deviceAlert.getDeviceAlertName());
                    }
                }else if("2".equals(deviceAlert.getDeviceAlertVal())){
                    if(StringToInteger(interfaceData)==StringToInteger(deviceAlert.getDeviceValue())){
                        SendMails(Constant.MailTest.testAccount,deviceAlert.getDeviceInterfaceId(),deviceAlert.getDeviceAlertName());
                    }
                }else if("3".equals(deviceAlert.getDeviceAlertVal())){
                    if(StringToInteger(interfaceData)<StringToInteger(deviceAlert.getDeviceValue())){
                        SendMails(Constant.MailTest.testAccount,deviceAlert.getDeviceInterfaceId(),deviceAlert.getDeviceAlertName());
                    }
                }else if("4".equals(deviceAlert.getDeviceAlertVal())){
                    if(StringToInteger(interfaceData)>=StringToInteger(deviceAlert.getDeviceValue())){
                        SendMails(Constant.MailTest.testAccount,deviceAlert.getDeviceInterfaceId(),deviceAlert.getDeviceAlertName());
                    }
                }else if("5".equals(deviceAlert.getDeviceAlertVal())){
                    if(StringToInteger(interfaceData)<=StringToInteger(deviceAlert.getDeviceValue())){
                        SendMails(Constant.MailTest.testAccount,deviceAlert.getDeviceInterfaceId(),deviceAlert.getDeviceAlertName());
                    }
                }else if("6".equals(deviceAlert.getDeviceAlertVal())){
                    if(StringToInteger(interfaceData)!=StringToInteger(deviceAlert.getDeviceValue())){
                        SendMails(Constant.MailTest.testAccount,deviceAlert.getDeviceInterfaceId(),deviceAlert.getDeviceAlertName());
                    }
                }
        }
    }

    @Override
    public ServerResponse getDeviceAlert(String id) {

        try{

        DeviceAlert deviceAlert = deviceAlertMapper.selectByPrimaryKey(id);
        return ServerResponse.createBySuccessMessage(deviceAlert);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }

    }

    @Override
    public ServerResponse updateDeviceAlert(DeviceAlert deviceAlert) {
        try{

            deviceAlertMapper.updateByPrimaryKeySelective(deviceAlert);
            return ServerResponse.createBySuccessMessage("success");
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }

    }


    private Integer StringToInteger(String intValue){

return Integer.parseInt(intValue);
}

    /**
     * @param target 邮件的发送目标
     * @param content 邮件的内容
     *                @param alertName 报警接口的名称
     */
    private void SendMails(String target,String content,String alertName){

        String subject = "报警提醒";
        String rContent = "编号为"+content+"名称为："+alertName+"的数据接口已经发生报警请及时处理。";
        mail.sendSimplMail(target,subject,rContent);
}

}
