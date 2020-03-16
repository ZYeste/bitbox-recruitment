package com.zebenyesterodriguez.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebenyesterodriguez.model.Product;
import com.zebenyesterodriguez.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> findAllByOrderByCodeAsc() {
		
		List<Product> products = productRepository.findAllByOrderByCodeAsc();
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		for(int i = 0; i < products.size(); i++) {
						
			String date = simpleDateFormat.format(products.get(i).getCreationDate());
			
			try {
				Product p = products.get(i);
				p.setCreationDate(simpleDateFormat.parse(date));
				products.set(i, p);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return products;
	}
	
	@Override
	public ArrayList<Product> findByStatus_id(Long id) {
		return productRepository.findByStatus_id(id);
	}
	
	@Override
	public void save(Product product) {
		productRepository.save(product);
	}
}
