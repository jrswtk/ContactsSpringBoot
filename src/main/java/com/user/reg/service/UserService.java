package com.user.reg.service;

import java.util.List;

import com.user.reg.domain.User;
import com.user.reg.exception.UserNotFoundException;

public interface UserService {

	User addUser(User user);
	User findById(long id) throws UserNotFoundException;
	User findByEmail(String email) throws UserNotFoundException;
	User updateUser(User user);
	void deleteUser(long id);
	List<User> findAll();
	
}
