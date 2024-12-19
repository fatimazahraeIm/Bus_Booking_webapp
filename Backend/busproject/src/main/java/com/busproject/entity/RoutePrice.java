package com.busproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "route_price")
public class RoutePrice {
	
	@Id
	@GeneratedValue
	@Column(name="price_id")
	private int id;
	
	private double basicCost;
	
	@Column(name="cost_per_km")
	private double costPerKM;
	
	public RoutePrice(int id, double basicCost, double costPerKM) {
		super();
		this.id = id;
		this.basicCost = basicCost;
		this.costPerKM = costPerKM;
	}

	public RoutePrice() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBasicCost() {
		return basicCost;
	}

	public void setBasicCost(double basicCost) {
		this.basicCost = basicCost;
	}

	public double getCostPerKM() {
		return costPerKM;
	}

	public void setCostPerKM(double costPerKM) {
		this.costPerKM = costPerKM;
	}
}
