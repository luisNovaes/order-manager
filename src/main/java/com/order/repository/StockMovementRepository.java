package com.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.model.Item;
import com.order.model.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long>{
	List<StockMovement> findByItemContaining(String item);
}
