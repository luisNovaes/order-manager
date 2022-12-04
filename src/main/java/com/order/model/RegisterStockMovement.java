package com.order.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RegisterStockMovement")
public class RegisterStockMovement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date DateMovement;

	@OneToOne(targetEntity=Order.class) 
	private Order orderId;
	
	private String situation;
	
	public RegisterStockMovement() {
	}

	
	public RegisterStockMovement(Date dateMovement, Order orderId, String situation) {
		DateMovement = dateMovement;
		this.orderId = orderId;
		this.situation = situation;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Date getDateMovement() {
		return DateMovement;
	}

	public void setDateMovement(Date dateMovement) {
		DateMovement = dateMovement;
	}

	public Order getOrderId() {
		return orderId;
	}

	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}	
	
}
