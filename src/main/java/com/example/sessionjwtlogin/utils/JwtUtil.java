package com.example.sessionjwtlogin.utils;

import java.time.Duration;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
	
	private final static String SECRET_KEY = "whatever";
	
	private final static Duration EXPIRATION = Duration.ofDays(2);
	
	/**
	 * 產生JWT
	 * @param userName，使用者名稱
	 * @return
	 */
	public static String generate(String userName) {
		Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION.toMillis());
		
//		Subject。username
//		IssuedAt。簽發時間
//		Expiration。過期時間
//		加密演算法和key
		return Jwts.builder()
					.setSubject(userName)
					.setIssuedAt(new Date())
					.setExpiration(expiryDate)
					.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
					.compact();
	}
	
	/**
	 * 解析JWT
	 * @param token，JWT字串
	 * @return
	 */
	public static Claims parse(String token) {
		if (token==null || (token!=null && token.equals(""))) {
			return null;
		}
		
//		token過期、token非法，都會拋出exception
		Claims claims = null;
		try {
			claims = Jwts.parser()
						.setSigningKey(SECRET_KEY)
						.parseClaimsJws(token)
						.getBody();
		} catch (Exception x) {
			LOGGER.error("[+] [Claims parse] error: {} ", x);
		}
		
		return claims;
	}
	
}
