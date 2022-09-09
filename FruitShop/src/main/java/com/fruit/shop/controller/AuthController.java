package com.fruit.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fruit.shop.DTO.JwtUtil;
import com.fruit.shop.DTO.SignInRequest;
import com.fruit.shop.DTO.SignUpRequest;
import com.fruit.shop.domain.User;
import com.fruit.shop.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private  JwtUtil jwtUtil;

	@PostMapping("/register")
	public User register(@RequestBody SignUpRequest request) {
		return userServiceImpl.register(request);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody SignInRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				request.getUsername(), request.getPassword());
		Authentication authentication = authenticationManager.authenticate(token);
		UserDetails details =(UserDetails)authentication.getPrincipal();
		User user = userServiceImpl.findUserByEmail(details.getUsername());
		
		String accessToken = jwtUtil.generateAccessToken(user);
		String refreshToken = jwtUtil.generateRefreshToken(user);
		
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", accessToken);
		tokens.put("refresh_token", refreshToken);
		return ResponseEntity.ok().body(tokens);
	}
	
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());
            if (jwtUtil.validate(refreshToken)) {
                UserDetails userDetails = (UserDetails) userServiceImpl.loadUserByUsername(jwtUtil.getUserEmail(refreshToken));
                User user = userServiceImpl.findUserByEmail(userDetails.getUsername());

                String accessToken = jwtUtil.generateAccessToken(user);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);

                return ResponseEntity.ok().body(tokens);
            }
        }

        return ResponseEntity.badRequest().body(null);
    }
}
