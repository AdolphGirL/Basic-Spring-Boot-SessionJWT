package com.example.sessionjwtlogin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.sessionjwtlogin.domain.bo.User;

/**
 * service層可以直接獲取session中的資料
 */
public class RequestContext {
	
	public static HttpServletRequest getCurrentRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	}
	
	public static User getCurrentUser() {
		return (User)getCurrentRequest().getSession().getAttribute("user");
	}
}
