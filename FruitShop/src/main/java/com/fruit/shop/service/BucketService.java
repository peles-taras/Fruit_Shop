package com.fruit.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruit.shop.domain.Bucket;
import com.fruit.shop.repo.BucketRepo;

@Service
public class BucketService {

	private final BucketRepo bucketRepo;
	
	@Autowired
	public BucketService(BucketRepo bucketRepo) {
		this.bucketRepo = bucketRepo;
	}
	
	public Bucket saveBucket(Bucket bucket) {
		return bucketRepo.save(bucket);
	}
	
	public Bucket findBucketById(Long id) {
		return bucketRepo.getReferenceById(id);
	}
	
	public List<Bucket> getAllBuckets(){
		return bucketRepo.findAll();
	}
	
	public void deleteBucketById(Long id) {
		bucketRepo.deleteById(id);
	}
}
