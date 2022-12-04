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
import com.order.model.StockMovement;
import com.order.repository.ItemRepository;
import com.order.repository.StockMovementRepository;
import com.order.repository.UserRepository;
import com.order.request.Request;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/stockMovement")
public class StockMovementController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockMovementController.class);
	@Autowired
	StockMovementRepository stockMovementRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/stockMovements")
	public ResponseEntity<List<StockMovement>> getAllStockMovements(@RequestParam(required = false) String stockMovement) {
		try {
			List<StockMovement> stockMovements = new ArrayList<StockMovement>();

			if (stockMovement == null)
				stockMovementRepository.findAll().forEach(stockMovements::add);
			else
				stockMovementRepository.findByItemContaining(stockMovement).forEach(stockMovements::add);

			if (stockMovements.isEmpty()) {
				LOGGER.warn("StockMovements not find");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			LOGGER.info("StockMovements find  sucess");
			return new ResponseEntity<>(stockMovements, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error system" + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/stockMovement/{id}")
	public ResponseEntity<StockMovement> getStockMovementById(@PathVariable("id") long id) {
		try {	Optional<StockMovement> StockMovementData = stockMovementRepository.findById(id);

		if (StockMovementData.isPresent()) {
			LOGGER.info("StockMovemen find  sucess");
			return new ResponseEntity<>(StockMovementData.get(), HttpStatus.OK);
		} else {
			LOGGER.error("StockMovemen Not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
		} catch (Exception e) {
			LOGGER.error("Error system" + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/stockMovement")
	public ResponseEntity<StockMovement> createStockMovement(@RequestBody Request request) {
		
		try {
			Item item = itemRepository.findByName(request.getItem());
			
			StockMovement _stockMovement  = stockMovementRepository
						.save(new StockMovement(new Date(), item, request.getQuantity()));
			LOGGER.info("StockMovement create sucess");
			return new ResponseEntity<>(_stockMovement, HttpStatus.CREATED);
			
		} catch (Exception e) {
			LOGGER.error("Error system stockMovement" + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/stockMovement/{id}")
	public ResponseEntity<StockMovement> updateStockMovement(@PathVariable("id") long id, @RequestBody Request request ) {
		try {
			Optional<StockMovement> StockMovementData = stockMovementRepository.findById(id);
			Item item = itemRepository.findByName(request.getItem());
			if (StockMovementData.isPresent()) {				
				StockMovement _stockMovement = StockMovementData.get();				
				_stockMovement.setCreationDate(new Date()); 
				_stockMovement.setItem(item);
				_stockMovement.setQuantity(request.getQuantity());		
				LOGGER.info("Update stockMovement sucess");
				return new ResponseEntity<>(stockMovementRepository.save(_stockMovement), HttpStatus.OK);				
			} else {
				LOGGER.error("Update stockMovement error: Not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			LOGGER.error("Error system stockMovement" + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/stockMovement/{id}")
	public ResponseEntity<HttpStatus> deleteStockMovement(@PathVariable("id") long id) {
		try {
			stockMovementRepository.deleteById(id);
			LOGGER.info("StockMovement ID " + id + " Deleted Sucess");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/stockMovement")
	public ResponseEntity<HttpStatus> deleteAllStockMovements() {
		try {
			stockMovementRepository.deleteAll();
			LOGGER.info("All stockMovement Deleteds Sucess");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Error system stockMovement" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
