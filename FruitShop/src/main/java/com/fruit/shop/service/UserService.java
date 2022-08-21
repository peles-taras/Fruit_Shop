package com.fruit.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruit.shop.domain.User;
import com.fruit.shop.repo.UserRepo;

@Service
public class UserService {

	private final UserRepo userRepo;

	@Autowired
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public User findUserById(Long id) {
		return userRepo.getReferenceById(id);
	}

	public User findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
	}

}
