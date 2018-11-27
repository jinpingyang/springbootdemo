package com.example.demo.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.annotation.AuthIgnore;
import com.example.demo.exception.AuthIgnoreException;
import com.example.demo.token.TokenUtil;

public class AuthIgnoreInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws AuthIgnoreException, IOException {
		if (handler == null) {
			return true;
		}
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod method = (HandlerMethod) handler;
		AuthIgnore authIgnore = method.getMethodAnnotation(AuthIgnore.class);
		if (authIgnore != null && authIgnore.value()) {

			String token = (String) request.getAttribute("token");
			if (token == null || token.equals("")) {
				response.sendRedirect("/signin");
//	        		throw new AuthIgnoreException("请登录");
				return false;
			}
			boolean exists = tokenUtil.existsToken(token);
			if (!exists) {
				response.sendRedirect("/signin");
//	        		throw new AuthIgnoreException("请登录");
				return false;
			}
			tokenUtil.refreshToken(token);
		}
		return true;
	}

}
