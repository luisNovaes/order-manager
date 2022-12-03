package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Iterable<Item> findByNameContaining(String item);
	
	Item findByName(String itemEntity);

}
