package com.fruit.shop.service;

import java.util.Optional;

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

	public Optional<User> findUserByEmail(String email) {
		return Optional.of(userRepo.findUserByEmail(email).get());
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
	}

}
