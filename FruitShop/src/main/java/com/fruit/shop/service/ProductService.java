package com.fruit.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruit.shop.domain.Product;
import com.fruit.shop.repo.ProductRepo;

@Service
public class ProductService {

	private final ProductRepo productRepo;

	@Autowired
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	public Product findProductById(Long id) {
		return productRepo.getReferenceById(id);
	}

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}
	
	public void deleteProductById(Long id) {
		productRepo.deleteById(id);
	}
}
