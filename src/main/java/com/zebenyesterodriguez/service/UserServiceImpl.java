package com.zebenyesterodriguez.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebenyesterodriguez.model.User;
import com.zebenyesterodriguez.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;	
	
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public Optional<User> findByToken(String token) {
		return userRepository.findByToken(token);
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public void save(User user) {	
        userRepository.save(user);
	}

}
