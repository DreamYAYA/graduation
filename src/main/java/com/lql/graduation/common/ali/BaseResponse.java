package com.lql.graduation.common.ali;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.RpcAcsRequest;

public class BaseResponse {

    private static DefaultAcsClient client;

    static {
        client = IotClient.getClient();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static AcsResponse executeTest(RpcAcsRequest request) {
        AcsResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            System.out.println("执行失败：e:" + e.getMessage());
        }
        return response;
    }




}
