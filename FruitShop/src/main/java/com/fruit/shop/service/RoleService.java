package com.fruit.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruit.shop.domain.Role;
import com.fruit.shop.repo.RoleRepo;

@Service
public class RoleService {

	private final RoleRepo roleRepo;

	@Autowired
	public RoleService(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}
	
	public Optional<Role> findRoleByName(String name) {
		return roleRepo.findByRoleName(name);
	}

}
