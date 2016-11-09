package com.user.reg.service;

import com.user.reg.domain.Profile;
import com.user.reg.security.RoleEnum;

public interface ProfileService {

	Profile findProfileByRole(RoleEnum role);
	
}
