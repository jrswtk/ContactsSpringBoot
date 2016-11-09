package com.user.reg.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.reg.domain.User;
import com.user.reg.exception.UserNotFoundException;
import com.user.reg.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User findById(long id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findOne(id);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}
	
	@Override
	public User findByEmail(String email) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findOneByEmail(email);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		User findUser = userRepository.findOne(user.getId());
		updateUserAttributes(user, findUser);
		userRepository.save(findUser);
		return findUser;
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		userRepository.delete(id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}
	
	private void updateUserAttributes(User user, User userToUpdate) {
		userToUpdate.setFirstName(user.getFirstName());
	}

}
