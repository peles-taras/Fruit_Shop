package com.fruit.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fruit.shop.domain.Bucket;

@Repository
public interface BucketRepo extends JpaRepository<Bucket, Long>{

}
