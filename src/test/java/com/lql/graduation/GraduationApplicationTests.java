package com.lql.graduation;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lql.graduation.common.Mail;
import com.lql.graduation.common.ScheduleQuartz;
import com.lql.graduation.common.ali.aliConfig;
import com.lql.graduation.common.ali.aliDevice;
import com.lql.graduation.mapper.UserMapper;
import com.lql.graduation.pojo.Message.DataBean;

import com.lql.graduation.pojo.Scheduler.ScheduleJob;
import com.lql.graduation.pojo.Scheduler.TimerJob;
import com.lql.graduation.pojo.User;
import com.lql.graduation.service.SendMessageService;
import com.lql.graduation.spring.config.GraduationApplication;
import com.lql.graduation.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraduationApplication.class)
public class GraduationApplicationTests {

	@Autowired
	public UserMapper userMapper;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Mail mail;

	@Autowired
	private ScheduleQuartz quartzManager;

	@Autowired
	private SendMessageService sendMessageService;


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
	@Test
          public void testJson() throws IOException {

		String payload = "{\"lastTime\":\"2017-12-12 10:10:56.757\",\"clientIp\":\"27.17.164.37\",\"time\":\"2017-12-12 10:10:56.772\",\"productKey\":\"jqLf0X9GFja\",\"deviceName\":\"device3\",\"status\":\"online\"}";
			  DataBean deviceData = new DataBean();
			  deviceData = (DataBean)JsonUtil.JsonToObject(payload, DataBean.class);

			  System.out.println(deviceData);


	}

	@Test
	public void sendSimplMail(){

		for (int i=0;i<10;i++){
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("836957413@qq.com");//发送者.
			message.setTo("1391228420@qq.com");//接收者.
			message.setSubject("测试邮件（邮件主题）");//邮件主题.
			message.setText("这是邮件内容");//邮件内容.
			mailSender.send(message);//发送邮件
		}

	}

	@Test
	public void mail(){
		mail.sendSimplMail("1391228420@qq.com","测试邮件（邮件主题","这是邮件内容");
	}


	@Test
	public void quartz() {

	}

	@Test
	public void test() throws SchedulerException {

		try {
			System.out.println("【系统启动】开始(每1秒输出一次 job2)...");

			Thread.sleep(5000);
			System.out.println("【增加job1启动】开始(每1秒输出一次)...");
			quartzManager.addJob("test", "test", "test", "test", TimerJob.class, "0 15 10 ? * 6 ","12");

//			Thread.sleep(10000);
//			System.out.println("【移除job1定时】开始...");
//			quartzManager.removeJob("test", "test", "test", "test");

			// 关掉任务调度容器
			 //quartzManager.shutdownJobs();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testCronException(){

		boolean validExpression = isValidExpression("0 25 9 ? * 6 ");
		System.out.println(validExpression);

	}


	public static boolean isValidExpression(final String cronExpression){
		CronTriggerImpl trigger = new CronTriggerImpl();
		try {
			trigger.setCronExpression(cronExpression);
			Date date = trigger.computeFirstFireTime(null);
			return date != null && date.after(new Date());
		} catch (Exception e) {
			System.out.println("[TaskUtils.isValidExpression]:failed. throw ex:");
		}
		return false;
	}


	@Test
	public void pubMessage(){

///jqLf0X9GFja/device3/get
		Boolean masg = sendMessageService.sendDeviceMessage("device3","LQL Hello","get");
		System.out.println(masg);

	}

}
