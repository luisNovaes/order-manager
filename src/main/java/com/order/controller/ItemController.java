package com.order.controller;

import java.util.ArrayList;
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

import com.order.model.Item;
import com.order.repository.ItemRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/item")
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String item) {
		try {
			List<Item> Items = new ArrayList<Item>();

			if (item == null)
				itemRepository.findAll().forEach(Items::add);
			else
				itemRepository.findByNameContaining(item).forEach(Items::add);

			if (Items.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Items, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") long id) {
		Optional<Item> ItemData = itemRepository.findById(id);

		if (ItemData.isPresent()) {
			return new ResponseEntity<>(ItemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/items")
	public ResponseEntity<Item> createItem(@RequestBody Item item) {
		try {
			Item _Item = itemRepository
					.save(new Item(item.getName()));
			return new ResponseEntity<>(_Item, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item item) {
		Optional<Item> itemData = itemRepository.findById(id);

		if (itemData.isPresent()) {
			Item _item = itemData.get();
			_item.setName(item.getName());
			return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
		try {
			itemRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/items")
	public ResponseEntity<HttpStatus> deleteAllItems() {
		try {
			itemRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
