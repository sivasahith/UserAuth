package com.interview.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.user.service.UserService;
import com.interview.user.vo.User;

@RestController
@RequestMapping("/v1/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param user
	 */
	@GetMapping
	public List<User> getAll() {
		LOGGER.debug("getAll(): Started.");
		List<User> users = this.userService.getAll();
		LOGGER.debug("getAll(): Ended.");
		return users;
	}
	
	/**
	 * 
	 * @param user
	 */
	@PostMapping
	public void registerUser(@RequestBody User user) {
		LOGGER.debug("register(): Started.");
		this.userService.registerUser(user);
		LOGGER.debug("register(): Ended.");
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		LOGGER.debug("getUserById(): Started.");
		User user = this.userService.getUserById(userId);
		ResponseEntity<User> res = null;
		if (user == null) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			res = new ResponseEntity<>(user, HttpStatus.OK);
		}
		LOGGER.debug("getUserById(): Ended.");
		return res;
	}

	/**
	 * 
	 * @param userId
	 * @param user
	 */
	@PutMapping("/{userId}")
	public void updateUser(@PathVariable String userId, @RequestBody User user) {
		LOGGER.debug("updateUser(): Started.");
		this.userService.updateUser(userId, user);
		LOGGER.debug("updateUser(): Ended.");
	}

	/**
	 * 
	 * @param userId
	 */
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable String userId) {
		LOGGER.debug("deleteUser(): Started.");
		this.userService.deleteUser(userId);
		LOGGER.debug("deleteUser(): Ended.");
	}
}
