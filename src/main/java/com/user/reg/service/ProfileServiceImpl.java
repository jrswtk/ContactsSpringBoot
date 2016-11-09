package com.user.reg.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.reg.domain.Profile;
import com.user.reg.repository.ProfileRepository;
import com.user.reg.security.RoleEnum;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Profile findProfileByRole(RoleEnum role) {
		// TODO Auto-generated method stub
		return profileRepository.getProfileByRole(role);
	}

}
