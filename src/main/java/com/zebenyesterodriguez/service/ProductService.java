package com.zebenyesterodriguez.service;

import java.util.ArrayList;
import java.util.List;

import com.zebenyesterodriguez.model.Product;

public interface ProductService {
	
	void save(Product product);

	List<Product> findAllByOrderByCodeAsc();
	
	Product findById(Long id);
	
	ArrayList<Product> findByStatus_id(Long id);

}
