package com.fruit.shop.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fruit.shop.DTO.CustomUserDetails;
import com.fruit.shop.DTO.SignUpRequest;
import com.fruit.shop.domain.Role;
import com.fruit.shop.domain.User;
import com.fruit.shop.service.RoleService;
import com.fruit.shop.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional 
@Slf4j 
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService{

	private final UserService userService;
	private final RoleService roleService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findUserByEmail(username);
		if (user == null) {
			log.error("User {} not found in the database", username);
			throw new UsernameNotFoundException("User not found in the databse");
		} else {
			log.info("User {} found in the database", username);
		}
		return CustomUserDetails.build(user);
	}
	
	public User register(SignUpRequest request) {
		User user = new User(request.getEmail(), request.getFirstName(), 
				request.getLastName(), request.getPassword());
		saveUser(user);
		return user;
	}
	
	public User saveUser(User user) {
		User userWithRole = addRoleToUser(user, Role.ROLE_USER);
		user.setPassword(passwordEncoder.encode(userWithRole.getPassword()));
		log.info("saving user: {} to the database", userWithRole.getEmail());
		return userService.saveUser(userWithRole);
		
	}
		
	
	public Role saveRole(Role role) {
		log.info("saving role: {} to the database", role.getRoleName());
		roleService.saveRole(role);
		return role;
	}
	
	public User addRoleToUser(User user, String name){
		Optional<Role> roleUser = roleService.findRoleByName(name);
		List<Role> roles = user.getRoles();

		if (roleUser.isEmpty()) {
			roleUser = Optional.of(saveRole(new Role(name)));
		} 
			roles.add(roleUser.get());
			user.setRoles(roles);
			log.info("adding role to the user:{}", user.getEmail());
		return user;
	}

	public User findUserById(Long id) {
		log.info("looking for user with id: {}", id);
		return userService.findUserById(id);
	}

	public User findUserByEmail(String email) {
		log.info("looking for user with email: {}", email);
		return userService.findUserByEmail(email).orElseThrow();
	}

	public User updateUser(User user) {
		log.info("updating user: {}", user.getEmail());
		return userService.updateUser(user);
	}

	public void deleteUserById(Long id) {
		log.info("deleting user with id: {}", id);
		userService.deleteUserById(id);
	}



}
