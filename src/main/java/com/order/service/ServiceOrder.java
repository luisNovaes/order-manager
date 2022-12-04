package com.order.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.model.Item;
import com.order.model.StockMovement;
import com.order.model.User;
import com.order.repository.ItemRepository;
import com.order.repository.StockMovementRepository;
import com.order.repository.UserRepository;
import com.order.request.Request;
import com.order.service.dto.ControllerDto;

@Service
public class ServiceOrder {
	
	@Autowired
	StockMovementRepository stockMovementRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	UserRepository userRepository;
	

	public ControllerDto buildOrder(Request request) {
		try {
			ControllerDto controllerDto = new ControllerDto();
			Item itemEntity = itemRepository.findByName(request.getItem());
			User userEntiry = userRepository.findByName(request.getUser());
			
			Optional<StockMovement> stock = stockMovementRepository.findById(itemEntity.getId());
			
			if (stock != null) {
					if(stock.get().getQuantity() >= request.getQuantity()) {
						
					controllerDto.setQuantity(request.getQuantity());
					controllerDto.setItem(itemEntity);
					controllerDto.setUser(userEntiry);
					
					upadateStockQtd(stock.get().getQuantity(), request.getQuantity(), stock.get());
					
					return controllerDto;
					
				} else {
					if (stock.get().getQuantity() != 0) {
						controllerDto.setQuantity(stock.get().getQuantity());
						controllerDto.setItem(itemEntity);
						controllerDto.setUser(userEntiry);
						upadateStockQtd(stock.get().getQuantity(), request.getQuantity(), stock.get());
						//serviço de Log.
						return controllerDto;
					}
					
				}

			}	
			
			
			
		} catch (Exception e) {
			//serviço de Log.
		}
		
		return null;

	}
	
	public void upadateStockQtd(Long qtdStock, Long qtdSRequest, StockMovement stockMovement) {
		
		try {
			stockMovement.setCreationDate(new Date());
			stockMovement.setItem(stockMovement.getItem());
			
			if (qtdStock - qtdSRequest <= 0) {
				stockMovement.setQuantity(0L);
			} else {
				stockMovement.setQuantity(qtdStock - qtdSRequest);
			}
			
			stockMovementRepository.saveAndFlush(stockMovement);
			
		} catch (Exception e) {
			//serviço de Log.
		}
				
		
		}



}
