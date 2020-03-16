package com.zebenyesterodriguez.service;

import java.util.Optional;

import com.zebenyesterodriguez.model.Status;

public interface StatusService {
	
	Optional<Status> findById(Long id);

}
