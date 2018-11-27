package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.redis.RedisUtil;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {
	
	
	 @Autowired
	 private RedisUtil redisUtil;
	 
	 @Autowired
	 private UserService userService;

	@Test
	public void contextLoads() {
//		User user=userService.findById(1);
//		boolean b=redisUtil.set("user", user);
//		System.out.println(b);
		
//		boolean b=redisUtil.expire("user");
//		System.out.println(b);
		
		boolean b=redisUtil.hasKey("user");
		System.out.println(b);
		
//		Object object=redisUtil.get("user");
//		User user=(User)object;
//		System.out.println(user.getId());
	}

}
