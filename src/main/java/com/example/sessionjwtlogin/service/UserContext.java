package com.example.sessionjwtlogin.service;

/**
 * 自行定義一個類，當成上下文。
 * 用來存放JWT(不像session驗證，直接存在session中)
 * FOR service層可以直接獲取
 */
public class UserContext {
	
//	ThreadLocal，防止衝突
	private static final ThreadLocal<String> USER_THREAD_LOCAL = new ThreadLocal<String>();
	
	public static void add(String userName) {
		USER_THREAD_LOCAL.set(userName);
	}

	public static void remove() {
		USER_THREAD_LOCAL.remove();
	}
	
//	取得目前線程登入的用戶資料
	public static String getCurrentUserName() {
		return USER_THREAD_LOCAL.get();
	}
	
}
