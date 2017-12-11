package com.lql.graduation;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lql.graduation.common.ali.aliConfig;
import com.lql.graduation.common.ali.aliDevice;
import com.lql.graduation.mapper.UserMapper;
import com.lql.graduation.pojo.Message.DataBean;
import com.lql.graduation.pojo.User;
import com.lql.graduation.spring.config.GraduationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraduationApplication.class)
public class GraduationApplicationTests {

     @Autowired
	public UserMapper userMapper;

	@Test
	public void contextLoads() {
		User user = new User();
		user.setUserName("liquanli");
		user.setUserPassword("zhangshan");
		User user1 = userMapper.queryUserByNameAndPass(user);
		System.out.println(user1.getUserId());

	}

	@Test
	public void ReviceMessage(){

		aliDevice  device = new aliDevice();
		try {
			device.CreateDeviceInIot("ss");
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ReviceQueMessage() throws IOException{

		aliConfig config = new aliConfig();
		CloudQueue queue = config.getQue("aliyun-iot-jqLf0X9GFja");
		while(!queue.isMessageNotExist(new ServiceException())){

			Message popMsg = queue.popMessage(10); //长轮询等待时间为10秒
			if (popMsg != null) {
				JsonToObject(popMsg.getMessageBodyAsString()); //获取原始消息
				queue.deleteMessage(popMsg.getReceiptHandle()); //从队列中删除消息
			} else {
				System.out.println("Continuing");
			}

		}
	}


	public void JsonToObject(String jsonStr) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(jsonStr);
		String payload = jsonNode.get("payload").toString();
		byte[] bytes = Base64Utils.decodeFromString(payload.replace("\"",""));
		String payloadStr = new String(bytes,"utf-8");
		//	DataBean dataBean = mapper.readValue(payloadStr, DataBean.class);
		System.out.println(jsonStr);
	//	System.out.println(dataBean);


	}

		@Test
		public void testBase64Decode() throws UnsupportedEncodingException {

String payload = "6KaB5Y+R6YCB55qE5pWw5o2u5YaF5a65LCDov5nkuKrlhoXlrrnlj6/ku6XmmK/ku7vmhI/moLzlvI8=";
			byte[] bytes = Base64Utils.decodeFromString(payload);
			String payloadStr = new String(bytes,"utf-8");
			System.out.println("payload:"+payloadStr);


		}


}
