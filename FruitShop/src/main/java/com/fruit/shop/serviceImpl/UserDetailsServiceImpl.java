//package com.fruit.shop.serviceImpl;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.fruit.shop.domain.User;
//import com.fruit.shop.security.CustomUserDetails;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Service @Slf4j
//public class UserDetailsServiceImpl implements UserDetailsService{
//
//	private  UserServiceImpl userServiceImpl;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userServiceImpl.findUserByEmail(username);
//		if (user == null) {
//			log.error("User {} not found in the database", username);
//			throw new UsernameNotFoundException("User not found in the databse");
//		} else {
//			log.error("User {} found in the database", username);
//		}
//		return CustomUserDetails.build(user);
//	}
//}
