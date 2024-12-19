package com.busproject.repo;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.busproject.entity.RouteHoraire;

public interface RouteHoraireRepo extends JpaRepository<RouteHoraire, Integer> {
	
	// To find all origin in a list
    @Query("SELECT DISTINCT horaire FROM route_horaire")
    List<LocalTime> findAllHoraire();
    

}
