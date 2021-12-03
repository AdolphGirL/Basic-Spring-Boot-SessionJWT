package com.example.sessionjwtlogin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.sessionjwtlogin.domain.bo.User;
import com.example.sessionjwtlogin.service.RequestContext;
import com.example.sessionjwtlogin.service.UserContext;
import com.example.sessionjwtlogin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public void doSomething() {
		User user = RequestContext.getCurrentUser();
		LOGGER.info("[+] [doSomething] user: {} ", user);
	}

	@Override
	public void doJwtSomething() {
		String currentUserName = UserContext.getCurrentUserName();
		LOGGER.info("[+] [doJwtSomething] currentUserName: {} ", currentUserName);
	}
	
}
