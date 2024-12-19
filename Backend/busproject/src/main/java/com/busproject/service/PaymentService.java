package com.busproject.service;

import java.util.List;

import com.busproject.entity.Payment;

public interface PaymentService {

	Payment save(Payment p);
	
	Payment findPaymemtByBookingID(int bookingId);
	
	List<Payment> findAllPayments();
}
