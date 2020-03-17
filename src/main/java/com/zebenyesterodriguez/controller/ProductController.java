package com.zebenyesterodriguez.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zebenyesterodriguez.model.Product;
import com.zebenyesterodriguez.model.ProductDeactivation;
import com.zebenyesterodriguez.model.Status;
import com.zebenyesterodriguez.model.User;
import com.zebenyesterodriguez.service.ProductDeactivationService;
import com.zebenyesterodriguez.service.ProductService;
import com.zebenyesterodriguez.service.StatusService;
import com.zebenyesterodriguez.service.UserService;
import com.zebenyesterodriguez.utils.TokenUtils;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDeactivationService productDeactivationService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private StatusService statusService;
	
	@GetMapping("products")
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET}, allowedHeaders = "*", allowCredentials = "true")
	public ResponseEntity<Object> product(@RequestParam(value="status", required=false) Long status) {
		
		ResponseEntity<Object> rEProduct = null;
		List<Product> products = null;
		
		if(status == null) {
			products = productService.findAllByOrderByCodeAsc();
		} else {
			products = productService.findByStatus_id(status);
		}
		
		rEProduct = new ResponseEntity<Object>(products, HttpStatus.OK);
		
		return rEProduct;
	}
	
	@PostMapping("products")
	@CrossOrigin(origins = "*", methods= {RequestMethod.POST})
	public ResponseEntity<Product> createProduct(@RequestParam("description") String description) {
		
		ResponseEntity<Product> rEProduct = null;
		User u = userService.findById(1l);
		Status s = statusService.findById(Status.ACTIVE).get();
		Product p = new Product();
		
		p.setDescription(description);
		p.setCreationDate(new Date());
		p.setUser(u);
		p.setStatus(s);
		p.setPrice(9);
		
		productService.save(p);
		
		rEProduct = new ResponseEntity<>(p, HttpStatus.OK);
		
		return rEProduct;
	}
	
	@GetMapping("products/{code}")
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
	public Product getProduct(@PathVariable Long code) {		
		return productService.findById(code);
	}

	@PostMapping("product/deactivate")
	@CrossOrigin(origins = "*", methods= {RequestMethod.POST})
	public ResponseEntity<ProductDeactivation> deactivateProduct(@RequestHeader("Authorization") String authorization, @RequestParam("code") Long code, @RequestParam("reason") String reason) {
		
		ResponseEntity<ProductDeactivation> rEProductDeactivation = null;
		ProductDeactivation productDeactivation = new ProductDeactivation();
		String token = TokenUtils.authToToken(authorization);

		Optional<User> optionalUser = userService.findByToken(token);
		Product product = productService.findById(code);
		Status status = statusService.findById(Status.DISCONTINUED).get();
		if(optionalUser.isPresent()) {
			
			productDeactivation.setUser(optionalUser.get());
			productDeactivation.setProduct(product);
			productDeactivation.setDate(new Date());
			productDeactivation.setReason(reason);
			
			// Insertar la desactivación del producto
			productDeactivationService.save(productDeactivation);
			
			// Actualizar el estado del producto
			product.setStatus(status);
			productService.save(product);
			
			rEProductDeactivation = new ResponseEntity<>(productDeactivation, HttpStatus.OK);
		} else {
			rEProductDeactivation = new ResponseEntity<>(productDeactivation, HttpStatus.FORBIDDEN);
		}

		return rEProductDeactivation;
	}
	
}
