package com.interview.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.user.vo.User;

@Service
public class UserSerivceImpl implements UserService {

	private static Map<String, User> userMap = new HashMap<>();

	@Override
	public void registerUser(User user) {
		userMap.put(user.getUserId(), user);
	}

	@Override
	public void updateUser(String userId, User user) {
		userMap.put(userId, user);
	}

	@Override
	public void deleteUser(String userId) {
		userMap.remove(userId);
	}

	@Override
	public User getUserById(String userId) {
		return userMap.get(userId);
	}

	@Override
	public List<User> getAll() {
		return new ArrayList<>(userMap.values());
	}

}
