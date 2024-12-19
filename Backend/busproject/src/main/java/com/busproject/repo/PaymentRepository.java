package com.busproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busproject.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	Payment findByBookingID(int bookingId);
}
