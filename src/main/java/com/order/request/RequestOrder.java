package com.order.request;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.order.model.Item;

public class RequestOrder {
	
	private long id;
	private Date creationDate;
	private String item;
	private Long quantity;
	private String user;
	
	public RequestOrder() {
	
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



	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}



	
}
