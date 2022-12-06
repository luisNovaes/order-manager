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

import com.order.model.User;
import com.order.repository.UserRepository;
import com.order.service.LoggerService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserRepository userRepository;
	@Autowired
	LoggerService loggerService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String user) {
		try {
			List<User> users = new ArrayList<User>();

			if (user == null)
				userRepository.findAll().forEach(users::add);
			else
				userRepository.findByNameContaining(user).forEach(users::add);

			if (users.isEmpty()) {
				loggerService.printErros("Users not found");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			LOGGER.info("User found  sucess");
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			loggerService.printErros("Error system user " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		try {
			Optional<User> userData = userRepository.findById(id);

			if (userData.isPresent()) {
				LOGGER.info("User found  sucess");
				return new ResponseEntity<>(userData.get(), HttpStatus.OK);
			} else {
				loggerService.printErros("User not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			loggerService.printErros("Error system user " + e);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User _user = userRepository
					.save(new User(user.getName(), user.getEmail()));
			LOGGER.info("User create sucess");
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			loggerService.printErros("Error system user " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		try {
			Optional<User> userData = userRepository.findById(id);
			if (userData.isPresent()) {
				User _user = userData.get();
				_user.setName(user.getName());
				_user.setEmail(user.getEmail());
				LOGGER.info("Update user sucess");
				return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
			} else {
				loggerService.printErros("Update user not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			loggerService.printErros("Error system user " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			LOGGER.info("user ID " + id + " Deleted user Sucess");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			loggerService.printErros("Error system user " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/user")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepository.deleteAll();
			LOGGER.info("All user Deleteds Sucess");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			loggerService.printErros("Error system users " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
