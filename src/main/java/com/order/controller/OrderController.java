package com.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.OrderManagerApplication;
import com.order.model.Item;
import com.order.model.Order;
import com.order.model.RegisterStockMovement;
import com.order.model.SituatioEnum;
import com.order.model.User;
import com.order.repository.ItemRepository;
import com.order.repository.OrderRepository;
import com.order.repository.RegistroMovementRepository;
import com.order.repository.UserRepository;
import com.order.request.Request;
import com.order.request.RequestOrder;
import com.order.response.Response;
import com.order.service.ServiceOrder;
import com.order.service.dto.ControllerDto;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	RegistroMovementRepository registroMovementRepository;
	@Autowired
	Response response;
	
	@Autowired
	ServiceOrder service;

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false) String order) {
		try {
			List<Order> orders = new ArrayList<Order>();

			if (order == null)
				orderRepository.findAll().forEach(orders::add);
			else
				orderRepository.findByUser(order).forEach(orders::add);

			if (orders.isEmpty()) {
				LOGGER.warn("Orders not find");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			LOGGER.info("Order find  sucess");
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error system" + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
		
		try {
			Optional<Order> OrderData = orderRepository.findById(id);

			if (OrderData.isPresent()) {
				LOGGER.info("Order find  sucess");
				return new ResponseEntity<>(OrderData.get(), HttpStatus.OK);
			} else {
				LOGGER.error("Order not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			LOGGER.error("Error system order" + e);
		}
		LOGGER.error("Error intput data!");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/order")
	public ResponseEntity<Order> createOrder(@RequestBody Request request) {
		Order orderEmpty = new Order();
		try {
			 ControllerDto orderDto = service.buildOrder(request);
			 if (orderDto == null) {
				 LOGGER.error("Erro order in processo operation: STOCK_MAVEMENT");
				 registroMovementRepository.save(new RegisterStockMovement(new Date(), null, SituatioEnum.ENDED_ERROR.toString()));
				 return new ResponseEntity<>(orderEmpty, HttpStatus.BAD_REQUEST);
			}
			Order _order = orderRepository.save(new Order(new Date(), orderDto.getItem(), orderDto.getQuantity(), orderDto.getUser()));
			RegisterStockMovement idMovement = registroMovementRepository.save(new RegisterStockMovement(new Date(), _order, SituatioEnum.SUCCESS_COMPLETED.toString()));
			orderDto.setCreationDate(new Date());
			orderDto.setId(_order.getId());
			response.enviarEmail(request, orderDto);
			LOGGER.info("Order create sucess");
			return new ResponseEntity<>(_order, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("Error system Order" + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") long id, @RequestBody RequestOrder requestOrder) {
		try {
			Optional<Order> OrderData = orderRepository.findById(id);
			
			Item item = itemRepository.findByName(requestOrder.getItem());
			
			User user = userRepository.findByName(requestOrder.getUser());

			if (OrderData.isPresent()) {
				Order _order = OrderData.get();
				_order.setUser(user);
				_order.setCreationDate(new Date());
				_order.setItem(item);
				_order.setQuantity(requestOrder.getQuantity());
				LOGGER.info("Update Order sucess");
				return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
			} else {
				LOGGER.error("Update Order error: Not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			LOGGER.error("Error system Order" + e);
		}
		return null;	
	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") long id) {
		try {
			orderRepository.deleteById(id);
			LOGGER.info("Order ID " + id + " Deleted Order Sucess");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Error system Order" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/order")
	public ResponseEntity<HttpStatus> deleteAllOrders() {
		try {
			orderRepository.deleteAll();
			LOGGER.info("All orders Deleteds Sucess");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Error of system Order" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
