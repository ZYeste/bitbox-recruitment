package com.zebenyesterodriguez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zebenyesterodriguez.model.ProductDeactivation;
import com.zebenyesterodriguez.service.ProductDeactivationService;

@RestController
public class ProductDeactivationController {

	@Autowired
	private ProductDeactivationService productDeactivationService;
	
	@GetMapping("productdeactivation/{code}")
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
	public List<ProductDeactivation> getProductDeactivation(@PathVariable Long code) {		
		return productDeactivationService.findByProduct_code(code);
	}	
}
