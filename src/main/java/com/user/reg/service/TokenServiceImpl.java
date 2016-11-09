package com.user.reg.service;

import java.math.BigInteger;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

	@Override
	public String generateToken() {
		// TODO Auto-generated method stub
		Random random = new Random();
		return new BigInteger(130, random).toString(32);
	}
	
}
