package com.busproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busproject.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
