package com.fruit.shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruit.shop.domain.User;
import com.fruit.shop.domain.UserRole;
import com.fruit.shop.service.UserService;

@Service
public class UserServiceImpl {

	private UserService userService;

	@Autowired
	public UserServiceImpl(UserService userService) {
		this.userService = userService;
	}

	public User saveUser(User user) {
		user.setRole(UserRole.USER);
		return userService.saveUser(user);
	}

	public User findUserById(Long id) {
		return userService.findUserById(id);
	}

	public User findUserByEmail(String email) {
		return userService.findUserByEmail(email);
	}

	public User updateUser(User user) {
		return userService.updateUser(user);
	}

	public void deleteUserById(Long id) {
		userService.deleteUserById(id);
	}

}
