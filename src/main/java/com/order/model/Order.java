package com.order.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "creationDate")
	private Date creationDate;
	
	@OneToOne(targetEntity=Item.class)  
	private Item item;
	
	@Column(name = "quantity")
	private Long quantity;
	
	
	@OneToOne(targetEntity=User.class)  
	private User user;

	public Order() {
	
	}

	public Order(Date creationDate, Item item, Long quantity, User user) {
		super();
		this.creationDate = creationDate;
		this.item = item;
		this.quantity = quantity;
		this.user = user;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Item getItem() {
		return item;
	}


	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public void setItem(Item item) {
		this.item = item;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
