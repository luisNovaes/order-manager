package com.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.order.model.Order;
import com.order.repository.OrderRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false) String order) {
		try {
			List<Order> orders = new ArrayList<Order>();

			if (order == null)
				orderRepository.findAll().forEach(orders::add);
			else
				orderRepository.findByUser(order).forEach(orders::add);

			if (orders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
		Optional<Order> OrderData = orderRepository.findById(id);

		if (OrderData.isPresent()) {
			return new ResponseEntity<>(OrderData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		try {
			Order _Order = orderRepository
					.save(new Order(order.getCreationDate(), order.getItem(), order.getQuantity(), order.getUser()));
			return new ResponseEntity<>(_Order, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {
		Optional<Order> OrderData = orderRepository.findById(id);

		if (OrderData.isPresent()) {
			Order _order = OrderData.get();
			_order.setUser(order.getUser());
			_order.setCreationDate(order.getCreationDate());
			_order.setItem(order.getItem());
			_order.setQuantity(order.getQuantity());
			return new ResponseEntity<>(orderRepository.save(_order), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") long id) {
		try {
			orderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/orders")
	public ResponseEntity<HttpStatus> deleteAllOrders() {
		try {
			orderRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
