package com.user.reg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.user.reg.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findOneByEmail(String email) throws UsernameNotFoundException;
	
}
