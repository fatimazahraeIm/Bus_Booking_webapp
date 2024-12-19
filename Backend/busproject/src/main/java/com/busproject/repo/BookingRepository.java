package com.busproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busproject.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
 
	List<Booking> getBookingsByUserID(int userID);
}
