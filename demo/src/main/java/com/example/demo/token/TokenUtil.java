package com.example.demo.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.redis.RedisUtil;
import com.example.demo.utils.UUIDUtil;

@Component
public class TokenUtil {
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * //认证之后用UUID生成token
	 * @param user
	 * @return
	 */
	public String getToken(User user) {
		String token=UUIDUtil.uuid();
		redisUtil.set(token, user);
		return token;
	}
	
	/**
	 * 刷新token
	 * @param token
	 * @return
	 */
	public Boolean refreshToken(String token) {
		return redisUtil.expire(token);
	}
	
	/**
	 * 退出，删除token
	 * @param token
	 * @return
	 */
	public Boolean logoff(String token) {
		return redisUtil.del(token);
	}
	
	/**
	 * 根据token，获取用户
	 * @param token
	 * @return
	 */
	public User getUser(String token) {
		return (User)redisUtil.get(token);
	}
	
	public boolean existsToken(String token) {
		return redisUtil.hasKey(token);
	}

}
