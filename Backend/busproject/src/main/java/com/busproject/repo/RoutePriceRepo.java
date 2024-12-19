package com.busproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busproject.entity.RoutePrice;


public interface RoutePriceRepo extends JpaRepository<RoutePrice, Integer> {

}
