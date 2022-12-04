package com.order.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.order.model.RegisterStockMovement;
import com.order.model.StockMovement;
import com.order.request.Request;
import com.order.service.dto.ControllerDto;

@Service
public class LoggerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerService.class);
	
	public void printTitle(String string) {
		
		LOGGER.info("*************************************************************");
		LOGGER.info("           ORDER MANAGER SYSTEM - CREATE ORDER            ");
		LOGGER.info("*************************************************************");
	}

	@SuppressWarnings("deprecation")
	public void detailOrder(Request request, ControllerDto orderDto, RegisterStockMovement idMovement, boolean sendEmail) {
		LOGGER.info("********************* ORDER CREATE SUCESS DETAIL ****************************************");
		LOGGER.info("Order number: " + orderDto.getId() + "                                                   ");
		LOGGER.info("Order Date: " + orderDto.getCreationDate().toLocaleString() + "                          ");
		LOGGER.info("Order User: " + orderDto.getUser().getName() + "                                         ");
		LOGGER.info("Order Item: " + orderDto.getItem().getName() + "                                         ");
		LOGGER.info("Order User: " + orderDto.getUser().getName() + "                                         ");
		LOGGER.info("Order The amount: " + orderDto.getQuantity()+ "                                          ");
		LOGGER.info("***************************************************************************");
		
		LOGGER.info("********************* STOCK MOVEMENT DETAIL *******************************");
		LOGGER.info("Stock movement number: " + idMovement.getId() + "                                            ");
		LOGGER.info("Stock movement Date: " + idMovement.getDateMovement() + "                                    ");
		LOGGER.info("Stock movement Order Number: " + idMovement.getOrderId().getId() + "                                  ");
		LOGGER.info("Stock movement Situation: " + idMovement.getSituation() + "                                  ");
		LOGGER.info("***************************************************************************");
		
		if (sendEmail) {
			LOGGER.info("********************* EMAIL SEND SUCESS ******************************************");
			LOGGER.info("Email: " + orderDto.getUser().getEmail() + "                                            ");
			LOGGER.info("***************************************************************************");
		}else {
			LOGGER.info("********************* EMAIL  NOT SEND ******************************************");
			LOGGER.info("Email: " + orderDto.getUser().getEmail() + "                                            ");
			LOGGER.info("***************************************************************************");
		}
		
	}

	public void resgisterErrorStokMovement(Optional<StockMovement> stock) {
		
		LOGGER.info("********************* ERROR CREATE ORDER ******************************************");
		LOGGER.info("Detail:Insufficient item.                                                               ");
		LOGGER.info("***************************************************************************************");
	
		LOGGER.info("*************STOCK MOVEMENT ITEM SITUATION*********************************************");
		LOGGER.error("Stock movemet date: " + stock.get().getCreationDate() + "                                            ");
		LOGGER.error("Stock movemet id : " + stock.get().getItem().getId() + "                                            ");
		LOGGER.error("Stock movemet name : " + stock.get().getItem().getName() + "                                            ");
		LOGGER.error("Stock movemet The amount: " + stock.get().getQuantity() + "                                            ");
		LOGGER.info("**************************************************************************************");
	}

	public void printErros(String erro) {
		LOGGER.info("********************* SYSTEM ERROR *************** ***********************************");
		LOGGER.error("Error detail: " + erro + "                                                            ");                                                             
		LOGGER.info("***************************************************************************************");
			
	}
		
}	
	
