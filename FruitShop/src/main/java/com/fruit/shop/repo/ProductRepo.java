package com.fruit.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fruit.shop.domain.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	
	
}
