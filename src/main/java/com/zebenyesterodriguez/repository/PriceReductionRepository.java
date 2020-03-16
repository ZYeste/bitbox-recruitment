package com.zebenyesterodriguez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zebenyesterodriguez.model.PriceReduction;

public interface PriceReductionRepository extends JpaRepository<PriceReduction, Integer>{	
	
}
