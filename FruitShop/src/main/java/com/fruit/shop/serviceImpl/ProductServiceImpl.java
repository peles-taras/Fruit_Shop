package com.fruit.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruit.shop.domain.Product;
import com.fruit.shop.service.ProductService;

@Service
public class ProductServiceImpl {

	private final ProductService productService;

	@Autowired
	public ProductServiceImpl(ProductService productService) {
		this.productService = productService;
	}

	public Product saveProduct(Product product) {
		return productService.saveProduct(product);
	}

	public Product findProductById(Long id) {
		return productService.findProductById(id);
	}

	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	public Product updateProduct(Product product) {
		return productService.updateProduct(product);
	}

	public void deleteProductById(Long id) {
		productService.deleteProductById(id);
	}

}
