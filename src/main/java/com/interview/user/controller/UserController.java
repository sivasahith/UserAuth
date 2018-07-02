package com.interview.user.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.interview.user.exception.UserNotFoundException;
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
	 * input - details of user output - created & Return the created URL
	 * 
	 * @param user
	 * @return
	 */

	@PostMapping
	public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
		LOGGER.debug("register(): Started.");
		this.userService.registerUser(user);
		LOGGER.debug("register(): Ended.");
		// CREATED
		// /user/{id} user.getUserId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.getUsername())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public Resource<User> getUserById(@PathVariable String userId) {
		LOGGER.debug("getUserById(): Started.");
		User user = this.userService.getUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("id-" + userId);
		}

		// "all-users", SERVER_PATH + "/users"
		// getAll

		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).getAll());

		resource.add(linkto.withRel("all-users"));

		return resource;
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
		User user = this.userService.getUserById(userId);

		if (user == null) {
			throw new UserNotFoundException("id-" + userId);

		} else {
			this.userService.deleteUser(userId);
		}

		LOGGER.debug("deleteUser(): Ended.");
	}
}
