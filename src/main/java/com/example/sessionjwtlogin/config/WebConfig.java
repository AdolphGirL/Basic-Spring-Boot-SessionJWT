package com.example.sessionjwtlogin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.sessionjwtlogin.filter.LoginInterceptor;

/**
 * WebMvcConfigurer公用
 * 
 * https://developer.aliyun.com/article/617307
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	/**
	 * 添加攔截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor());
	}
	
}
