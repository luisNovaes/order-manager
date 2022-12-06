package com.order.controller;

import java.util.ArrayList;
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

import com.order.model.Item;
import com.order.repository.ItemRepository;
import com.order.service.LoggerService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/item")
public class ItemController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	LoggerService loggerService;

	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String item) {
		try {
			List<Item> Items = new ArrayList<Item>();

			if (item == null)
				itemRepository.findAll().forEach(Items::add);
			else
				itemRepository.findByNameContaining(item).forEach(Items::add);

			if (Items.isEmpty()) {
				loggerService.printErros("Item not found");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			LOGGER.info("Items found  sucess");
			return new ResponseEntity<>(Items, HttpStatus.OK);
		} catch (Exception e) {
			loggerService.printErros("Error system " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") long id) {
		try {
			Optional<Item> ItemData = itemRepository.findById(id);

			if (ItemData.isPresent()) {
				LOGGER.info("Item found  sucess");
				return new ResponseEntity<>(ItemData.get(), HttpStatus.OK);
			} else {
				loggerService.printErros("Item not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			loggerService.printErros("Error system " + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/item")
	public ResponseEntity<Item> createItem(@RequestBody Item item) {
		try {
			Item _Item = itemRepository
					.save(new Item(item.getName()));
			LOGGER.info("Item create sucess");
			return new ResponseEntity<>(_Item, HttpStatus.CREATED);
		} catch (Exception e) {
			loggerService.printErros("Erro in processo operation: CREATE_ITEM");
			loggerService.printErros("Error system Item " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/item/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item item) {
		try {
			Optional<Item> itemData = itemRepository.findById(id);
			if (itemData.isPresent()) {
				Item _item = itemData.get();
				_item.setName(item.getName());
				LOGGER.info("Update sucess");
				return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
			} else {
				loggerService.printErros("Update error: Item not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			loggerService.printErros("Error system " + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/item/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
		try {
			itemRepository.deleteById(id);
			LOGGER.info("Item ID " + id + " Deleted Sucess");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			loggerService.printErros("Error system" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/items")
	public ResponseEntity<HttpStatus> deleteAllItems() {
		try {
			itemRepository.deleteAll();
			LOGGER.info("All items Deleteds Sucess ");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			loggerService.printErros("Error system" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
