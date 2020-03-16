package com.zebenyesterodriguez.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zebenyesterodriguez.model.User;

public interface UserRepository extends JpaRepository<User, Long>{		

	Optional<User> findByToken(String token);

	Optional<User> findByUsername(String username);
	
}
