package com.zebenyesterodriguez.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebenyesterodriguez.model.Status;
import com.zebenyesterodriguez.repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public Optional<Status> findById(Long id) {
		return statusRepository.findById(id);
	}
}
