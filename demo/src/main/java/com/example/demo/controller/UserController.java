package com.example.demo.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.User;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="/user",tags="用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public Page<com.example.demo.entity.User> findByAll(HttpServletRequest request){
		Map<String, String[]> requestParams=request.getParameterMap();
        Map<String,Object> params=new HashMap<String,Object>();
        for(Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            String key=entry.getKey();
            String value=entry.getValue()[0];
            for (Field field : User.class.getDeclaredFields()) {
                if(field.getName().equals(key)){
                    params.put(key,value);
                }
            }
//            for(Field field : SoftwareVersion.class.getSuperclass().getDeclaredFields()){
//                if(field.getName().equals(key)){
//                    params.put(key,value);
//                }
//            }
        }
        Pageable pageable=null;
        if(requestParams.get("pageNum")!=null && requestParams.get("pageCount")!=null){
        	pageable = PageRequest.of(Integer.valueOf(requestParams.get("pageNum")[0]), Integer.valueOf(requestParams.get("pageCount")[0]),Sort.Direction.DESC, "id");
        }
        return userService.findByAll(params,pageable);
	}

	@ApiOperation(value="/getUser",notes="查询用户")
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	@ResponseBody
	public User getUser() {
		User user=new User();
		user.setId(1);
		user.setNo("001");
		user.setName("zhangsan");
		user.setAddress("shanghai");
		return user;
	}
	
	@ApiOperation(value="/getUserId",notes="查询用户")
	@ApiImplicitParams(value= {@ApiImplicitParam(name="id",value="用户Id",required=true,dataType="int")})
	@RequestMapping(value="/getUserId",method=RequestMethod.GET)
	@ResponseBody
	public User getUserId(@RequestParam int id) {
		System.out.println(id);
		User user=new User();
		user.setId(1);
		user.setNo("001");
		user.setName("zhangsan");
		user.setAddress("shanghai");
		return user;
	}
	
	@ApiOperation(value="/addUser",notes="添加用户")
	@ApiImplicitParams(value= {@ApiImplicitParam(name="user",value="用户信息",required=true)})
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(@RequestBody User user) {
		System.out.println("---------------------------------");
		System.out.println(user.getId());
		System.out.println(user.getName());
		return "sussces";
	}
	
}
