package com.example.sessionjwtlogin.filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.sessionjwtlogin.service.UserContext;
import com.example.sessionjwtlogin.utils.JwtUtil;

import io.jsonwebtoken.Claims;

/**
 * 需要實現，WebMvcConfigurer接口，HandlerInterceptor加入
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if ("/session-jwt-login/jwt/login".equals(request.getRequestURI())) {
			return true;
		}

		Claims claims = JwtUtil.parse(request.getHeader("Authorization"));
		if (claims != null) {
			UserContext.add(claims.getSubject());
			return true;
		}
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write("請先登入All");
		out.flush();
		out.close();
		
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
//		請求結束後，要刪掉。不然ThreadLocal會爆掉
		UserContext.remove();
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
