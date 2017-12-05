package com.lql.graduation;

import com.lql.graduation.mapper.UserMapper;
import com.lql.graduation.pojo.User;
import com.lql.graduation.spring.config.GraduationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
