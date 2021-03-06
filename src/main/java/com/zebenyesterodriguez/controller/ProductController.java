package com.zebenyesterodriguez.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
	
	@PostMapping("products/add")
	@CrossOrigin(origins = "*", methods= {RequestMethod.POST})
	public ResponseEntity<Object> addProduct(@RequestHeader(value="Authorization") String authorizationHeader, @RequestParam("description") String description) {
		
		ResponseEntity<Object> rEProduct = null;
		String token = TokenUtils.authToToken(authorizationHeader);
		Status s = statusService.findById(Status.ACTIVE).get();
		Product p = new Product();
		Optional<User> optional = userService.findByToken(token);
		User u = null;
				
		if(optional.isPresent()){
			if(description.isEmpty()){
				rEProduct = new ResponseEntity<>("Empty product description", HttpStatus.FORBIDDEN);
			} else {
				
				u = optional.get();
				
				p.setDescription(description);
				p.setCreationDate(new Date());
				p.setUser(u);
				p.setStatus(s);
				p.setPrice(0);
				
				productService.save(p);
				
				rEProduct = new ResponseEntity<>(p, HttpStatus.OK);
			}
		} else {
			rEProduct = new ResponseEntity<>("Invalid security token", HttpStatus.FORBIDDEN);
		}
		
		return rEProduct;
	}
	
	@GetMapping("products/{code}")
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
	public Product getProduct(@PathVariable Long code) {		
		return productService.findById(code);
	}

	@GetMapping("products")
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET}, allowedHeaders = "*", allowCredentials = "true")
	public ResponseEntity<Object> getProducts(@RequestParam(value="status", required=false) Long status) {
		
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
