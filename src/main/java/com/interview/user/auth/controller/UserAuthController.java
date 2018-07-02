package com.interview.user.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping("/v1/user/authenticate")
	public ResponseEntity<User> authenticate(@RequestBody User userReq) {
		User user = this.userSerivce.getUserById(userReq.getUsername());
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (!userReq.getPassword().equalsIgnoreCase(user.getPassword())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		user.setToken("token");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
