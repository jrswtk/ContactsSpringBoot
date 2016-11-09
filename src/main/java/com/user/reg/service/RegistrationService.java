package com.user.reg.service;

import java.util.List;

import com.user.reg.domain.Registration;
import com.user.reg.exception.RegistrationNotFoundException;

public interface RegistrationService {

	Registration addRegistration(Registration registration);
	Registration findById(long id) throws RegistrationNotFoundException;
	Registration findByEmail(String email) throws RegistrationNotFoundException;
	Registration updateRegistration(Registration registration);
	void deleteRegistration(long id);
	List<Registration> findAll();
	
}
