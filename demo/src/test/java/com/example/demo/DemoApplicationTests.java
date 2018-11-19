package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
		System.out.println(redisUtil.get("b"));
		System.out.println(redisUtil.hasKey("a"));

		userService.findById(1);
	}

}
