package com.lql.graduation.common.ali;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20170420.RegistDeviceRequest;
import com.aliyuncs.iot.model.v20170420.RegistDeviceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lql.graduation.util.Constant;

public class aliDevice {

    String accessKey = Constant.aliDevice.ACCESS_KEY;
    String accessSecret = Constant.aliDevice.ACCESS_SCREAT;
    public aliDevice() {


    }

    public RegistDeviceResponse CreateDeviceInIot(String deviceName) throws ClientException {
        DefaultAcsClient client = InitClient();
       RegistDeviceRequest request = new RegistDeviceRequest();
       request.setProductKey(Constant.aliDevice.PRODUCT_KEY);
       request.setDeviceName(deviceName);//可以设空，如果名称为空则由阿里云生成设备名称默认与设备id一致,设备名称在产品内唯一
       RegistDeviceResponse resp = client.getAcsResponse(request);

       System.out.println(resp.getSuccess());
       System.out.println(resp.getErrorMessage());
       System.out.println(resp.getDeviceSecret());
       System.out.println(resp.getDeviceId());
       System.out.println(resp.getDeviceName());
            return resp;
   }

    private DefaultAcsClient InitClient() throws ClientException {
        DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
        return new DefaultAcsClient(profile);
    }



}
