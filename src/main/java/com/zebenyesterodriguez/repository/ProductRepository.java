package com.zebenyesterodriguez.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zebenyesterodriguez.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{	

	ArrayList<Product> findByStatus_id(Long id);
	
	ArrayList<Product> findAllByOrderByCodeAsc();
	
}
