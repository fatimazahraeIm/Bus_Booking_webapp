package com.busproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busproject.entity.Cancellation;

public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {
    
}
