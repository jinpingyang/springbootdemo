package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@SpringBootApplication
@EnableSwagger2
@EnableCaching // 启动缓存
public class DemoApplication {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("启动。。。。。。。");
	}
	
	@RequestMapping("/test1")
	public String test1() {
		return "/index";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "index.jsp";
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser() {
		User user=new User();
		user.setId(1);
		user.setNo("001");
		user.setName("zhangsan");
		user.setAddress("shanghai");
		return user;
	}
	
	@RequestMapping("/getUsers")
	@ResponseBody
	public List<User> getUsers() {
		List<User> list=new ArrayList<User>();
		User user=new User();
		user.setId(1);
		user.setNo("001");
		user.setName("zhangsan");
		user.setAddress("shanghai");
		list.add(user);
		User user1=new User();
		user1.setId(2);
		user1.setNo("002");
		user1.setName("zhangsan");
		user1.setAddress("shanghai");
		list.add(user1);
		return list;
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(@RequestBody User user) {
		System.out.println("---------------------------------");
		System.out.println(user.getId());
		System.out.println(user.getName());
		return "sussces";
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public com.example.demo.entity.User findById(Integer id){
		return userService.findById(id);
	}
}
