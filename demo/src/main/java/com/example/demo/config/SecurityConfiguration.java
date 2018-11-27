package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.utils.MyPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	  
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		//5版本后密码支持多种加密格式
		auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
			.withUser("admin")
			.password("1")
			.roles("ADMIN");
		
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.withUser("user").password(new BCryptPasswordEncoder().encode("1"))
		.roles("USRE");

		
//		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	
	/**
	 * 具体的权限控制规则配置
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll() //任何用户可以访问
                .anyRequest().authenticated() //任何没有匹配上的其他的url请求，只需要用户被验证。
                .and().formLogin()/**.loginPage("/login")//指定登陆页面，不指定跳转spring登陆页面*/
//                .failureForwardUrl("/login") //登录失败后以登录时的请求转发到该链接
//                .successHandler(successHandler) //登录成功后调用该处理器
//                .successForwardUrl("/test")
                .permitAll()
                .and().logout().permitAll() 
//                .logoutSuccessUrl("/login") //退出后访问URL
                .and().httpBasic()
                .and().csrf().disable();
    }
    
    
    /**
              * 全局请求忽略规则配置
     */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		   .antMatchers(HttpMethod.OPTIONS, "/**")
		   .antMatchers( "/v2/api-docs","/swagger-resources/**","/swagger-ui.html**","/webjars/**")
		   .antMatchers("/signin");
	}


}
