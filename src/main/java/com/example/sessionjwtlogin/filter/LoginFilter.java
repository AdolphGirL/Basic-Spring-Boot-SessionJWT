package com.example.sessionjwtlogin.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sessionjwtlogin.domain.bo.User;

//測試jwt，先關掉
//@Component
public class LoginFilter extends OncePerRequestFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		LOGGER.info("[+] [doFilterInternal] URI: {} ", request.getRequestURI());
		
		if ("/session-jwt-login/login".equals(request.getRequestURI())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write("請先登入All");
		out.flush();
		out.close();
	}

}
