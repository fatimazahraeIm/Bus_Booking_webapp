package com.busproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busproject.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
}
