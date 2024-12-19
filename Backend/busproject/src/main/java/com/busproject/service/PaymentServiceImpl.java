package com.busproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busproject.entity.Payment;
import com.busproject.repo.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository repo;
	@Override
	public Payment save(Payment p) {
		return repo.save(p);
	}
	
	@Override
	public Payment findPaymemtByBookingID(int bookingId) {
		return repo.findByBookingID(bookingId);
	}

	@Override
	public List<Payment> findAllPayments() {
		return repo.findAll();
	}


}
