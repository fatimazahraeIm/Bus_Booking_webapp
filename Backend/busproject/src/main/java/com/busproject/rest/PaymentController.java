package com.busproject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busproject.entity.Payment;
import com.busproject.service.PaymentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	@PostMapping(value = "/" ,consumes = "application/json")
	public Payment add(@RequestBody Payment p) {
		return service.save(p);
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	public Payment get(@PathVariable int id) {
		return service.findPaymemtByBookingID(id);
	}
	
	@GetMapping(value="/all", produces = "application/json")
	public List<Payment> getAll() {
		return service.findAllPayments();
	}
	
	
}
