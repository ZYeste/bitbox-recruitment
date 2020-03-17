package com.zebenyesterodriguez.service;

import java.util.ArrayList;

import com.zebenyesterodriguez.model.ProductDeactivation;

public interface ProductDeactivationService {
	
	void save(ProductDeactivation productDeactivation);
	
	ArrayList<ProductDeactivation> findByProduct_code(Long code);

}
