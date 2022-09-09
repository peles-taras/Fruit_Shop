package com.fruit.shop.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fruit.shop.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	
Optional<Role> findByRoleName(String roleName);
	
}
