package com.user.reg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.reg.domain.Registration;
import com.user.reg.exception.RegistrationNotFoundException;
import com.user.reg.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Override
	public Registration addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		return registrationRepository.save(registration);
	}

	@Override
	public Registration findById(long id) {
		// TODO Auto-generated method stub
		return registrationRepository.findOne(id);
	}

	@Override
	public Registration findByEmail(String email) throws RegistrationNotFoundException {
		// TODO Auto-generated method stub
		Registration registration = registrationRepository
				.findOneByEmail(email);
		
		if(registration == null) {
			throw new RegistrationNotFoundException();
		}
		
		return registration;
	}

	@Override
	public Registration updateRegistration(Registration registration) {
		// TODO Auto-generated method stub
		Registration findRegistration = registrationRepository.findOne(
				registration.getId());
		updateRegistrationAttributes(registration, findRegistration);
		registrationRepository.save(findRegistration);
		return findRegistration;
	}

	@Override
	public void deleteRegistration(long id) {
		// TODO Auto-generated method stub
		registrationRepository.delete(id);
	}

	@Override
	public List<Registration> findAll() {
		// TODO Auto-generated method stub
		return (List<Registration>) registrationRepository.findAll();
	}
	
	private void updateRegistrationAttributes(Registration registration, 
				Registration registrationToUpdate) {
		
		registrationToUpdate.setEmail(registration.getEmail());
		registrationToUpdate.setToken(registration.getToken());
	}

}
