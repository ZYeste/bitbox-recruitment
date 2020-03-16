package com.zebenyesterodriguez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zebenyesterodriguez.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{	
	
}
