package com.user.reg.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean match(String password, String passwordDataBase) {
		return password.equals(passwordDataBase);
	}
	
	public String getEncryptPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
}
