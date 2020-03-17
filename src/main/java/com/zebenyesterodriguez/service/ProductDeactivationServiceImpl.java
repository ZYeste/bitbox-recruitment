package com.zebenyesterodriguez.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebenyesterodriguez.model.ProductDeactivation;
import com.zebenyesterodriguez.repository.ProductDeactivationRepository;

@Service
public class ProductDeactivationServiceImpl implements ProductDeactivationService {

	@Autowired
	private ProductDeactivationRepository productDeactivationRepository;
	
	@Override
	public void save(ProductDeactivation productDeactivation) {
		productDeactivationRepository.save(productDeactivation);
	}
	
	@Override
	public ArrayList<ProductDeactivation> findByProduct_code(Long code) {
		return productDeactivationRepository.findByProduct_code(code);
	}
		
}
