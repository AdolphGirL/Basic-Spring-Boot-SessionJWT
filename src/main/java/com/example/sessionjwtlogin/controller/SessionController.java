package com.example.sessionjwtlogin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sessionjwtlogin.domain.bo.User;
import com.example.sessionjwtlogin.service.UserService;

@RestController
public class SessionController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("login")
	public String login(@RequestBody User user, HttpSession session) {
		if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
			session.setAttribute("user", user);
			return "登入成功";
		}

		return "帳號密碼有誤";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "登出成功";
	}
	
	@GetMapping("api1")
	public String api1(HttpSession session) {
//		改由filter檢核
//		User user = (User) session.getAttribute("user");
//		if (user == null) {
//			return "請先登入1";
//		}
		
		return "成功返回數據1";
	}
	
	@GetMapping("api2")
	public String api2(HttpSession session) {
//		改由filter檢核
//		User user = (User) session.getAttribute("user");
//		if (user == null) {
//			return "請先登入2";
//		}
		
		return "成功返回數據2";
	}
	
	/**
	 * 測試 service層，調用session資料
	 * @return
	 */
	@GetMapping("api3")
	public String api3() {
		this.userService.doSomething();
		return "成功返回數據3";
	}
}
