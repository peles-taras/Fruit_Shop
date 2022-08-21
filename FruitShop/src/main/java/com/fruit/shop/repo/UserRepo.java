package com.fruit.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fruit.shop.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findUserByEmail(String email);
}
