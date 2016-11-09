package com.user.reg.repository;

import org.springframework.data.repository.CrudRepository;

import com.user.reg.domain.Profile;
import com.user.reg.security.RoleEnum;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

	Profile getProfileByRole(RoleEnum role);
		
}
