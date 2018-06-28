package com.interview.user.service;

import java.util.List;

import com.interview.user.vo.User;

public interface UserService {

	public List<User> getAll();
	
	/**
	 * 
	 * @param user
	 */
	public void registerUser(User user);

	/**
	 * 
	 * @param userId
	 * @param user
	 */
	public void updateUser(String userId, User user);

	/**
	 * 
	 * @param userId
	 */
	void deleteUser(String userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	User getUserById(String userId);
}
