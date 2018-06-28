package com.interview.user.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.user.service.UserService;
import com.interview.user.vo.User;

@RestController
public class UserAuthController {

	@Autowired
	private UserService userSerivce;

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	@GetMapping("/authenticate")
	public ResponseEntity<User> authenticate(String userId, String password) {
		User user = this.userSerivce.getUserById(userId);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (!password.equalsIgnoreCase(user.getPassword())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
