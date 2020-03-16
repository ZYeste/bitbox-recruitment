package com.zebenyesterodriguez.service;

import java.util.Optional;

import com.zebenyesterodriguez.model.User;

public interface UserService {
	
	void save(User user);

	User findById(Long id);
	
	Optional<User> findByToken(String token);
	
	Optional<User> findByUsername(String username);

}
