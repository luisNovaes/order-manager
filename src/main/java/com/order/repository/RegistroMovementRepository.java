package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.model.Item;
import com.order.model.RegisterStockMovement;

public interface RegistroMovementRepository extends JpaRepository<RegisterStockMovement, Long> {

}
