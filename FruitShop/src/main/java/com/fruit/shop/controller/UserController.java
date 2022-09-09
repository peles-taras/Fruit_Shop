package com.fruit.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fruit.shop.domain.User;
import com.fruit.shop.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserServiceImpl userServiceImpl;

	@Autowired
	public UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}


	@GetMapping("/find/{id}")
	public ResponseEntity<User> getUserByid(@PathVariable("id") Long id) {
		User user = userServiceImpl.findUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/get/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
		User user = userServiceImpl.findUserByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User updateUser = userServiceImpl.updateUser(user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		userServiceImpl.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
