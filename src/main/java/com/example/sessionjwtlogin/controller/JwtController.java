package com.example.sessionjwtlogin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sessionjwtlogin.domain.bo.User;
import com.example.sessionjwtlogin.service.UserService;
import com.example.sessionjwtlogin.utils.JwtUtil;

@RestController
public class JwtController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 測試模擬，不連線資料庫
	 * @param user
	 * @return
	 */
	@PostMapping("/jwt/login")
	public String login(@RequestBody User user) {
		if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
			return JwtUtil.generate(user.getUsername());
		}
		
		return "帳號密碼有誤";
	}
	
	/**
	 * 前端token，一般會放在header Authorization，格式為 類型 + token；
	 * 目前測試使用直接將token放在Authorization
	 * @param request
	 * @return
	 */
	@GetMapping("/jwt/api1")
	public String api1(HttpServletRequest request) {
//		交由攔截器，HandlerInterceptorAdapter處理
//		String jwt = request.getHeader("Authorization");
//		if (JwtUtil.parse(jwt) == null) {
//			return "請先登入";
//		}
		
		return "成功返回數據1";
	}
	
	@GetMapping("/jwt/api2")
	public String api2(HttpServletRequest request) {
//		交由攔截器，HandlerInterceptorAdapter處理
//		String jwt = request.getHeader("Authorization");
//		if (JwtUtil.parse(jwt) == null) {
//			return "請先登入";
//		}
		
		return "成功返回數據2";
	}
	
	/**
	 * 測試 service層，調用session資料
	 * @return
	 */
	@GetMapping("/jwt/api3")
	public String api3() {
		this.userService.doJwtSomething();
		return "成功返回數據3";
	}
}
