package com.zebenyesterodriguez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zebenyesterodriguez.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{	
	
}
