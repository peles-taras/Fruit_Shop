package com.fruit.shop.DTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fruit.shop.domain.User;

@Component
@PropertySource("classpath:application.properties")
public class JwtUtil {

	@Value("${JWT.secret}")
	private String jwtSecret;
	
	@Value("${JWT.issuer}")
	private String jwtIssuer;
	
	@Value("${JWT.acessTokenExpireTime}")
	private int jwtAccessTokenExpireTime;
	
	@Value("${JWT.refreshTokenExpireTime}")
	private int jwtRefreshTokenExpireTime;
 
	public String generateAccessToken(User user) {
		Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());

		List<String> authorities = new ArrayList<>();
	       user.getRoles().forEach(role -> {
	            authorities.add(role.getRoleName());
	        });
		
	return	JWT.create()
			.withSubject(String.format("%s,%s", user.getId(), user.getEmail()))
			.withIssuer(jwtIssuer)
			.withIssuedAt(new Date())
			.withExpiresAt(new Date(System.currentTimeMillis() + jwtAccessTokenExpireTime * 60 * 1000))	
			.withClaim("roles", authorities)
	        .sign(algorithm);
	}
	
	
	public String generateRefreshToken(User user) {
		Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());

	return	JWT.create()
			.withSubject(String.format("%s,%s", user.getId(), user.getEmail()))
			.withIssuer(jwtIssuer)
			.withExpiresAt(new Date(System.currentTimeMillis() + jwtRefreshTokenExpireTime * 60 * 1000))	
	        .sign(algorithm);
	}
	
	
	public boolean validate(String token) {
		try {
			long expiresAt = JWT.decode(token).getExpiresAt().getTime();
			Calendar calendar = Calendar.getInstance();
			long time = calendar.getTime().getTime();
			if (expiresAt >= time) {
				return true;
			}
		} catch (IllegalArgumentException e) {
			String.format("Token {%s} is not valid", e.getMessage());
		}
		return false;
	}
	
	public String getUserEmail(String token) {
		String subject = JWT.decode(token).getSubject();
		return subject.split(",")[1];
	}
}
