package com.zebenyesterodriguez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zebenyesterodriguez.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{	
	
}
