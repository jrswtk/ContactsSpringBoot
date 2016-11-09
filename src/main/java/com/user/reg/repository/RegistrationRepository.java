package com.user.reg.repository;

import org.springframework.data.repository.CrudRepository;

import com.user.reg.domain.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, Long> {
	
	Registration findOneByEmail(String email);
	
}
