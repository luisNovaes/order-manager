package com.order.service.dto;

import java.util.Date;

import com.order.model.Item;
import com.order.model.User;

public class ControllerDto {
	
	private long id;
	private Date creationDate;
	private Item item;
	private Long quantity;
	private User user;
	
	public ControllerDto() {
	
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


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Item getItem() {
		return item;
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
