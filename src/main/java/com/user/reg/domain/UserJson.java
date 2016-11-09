package com.user.reg.domain;

import com.user.reg.utils.JsonUtil;

public class UserJson implements JsonUtil<User, UserJson> {
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String role;

	public UserJson() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public UserJson convertToJson(User user) {
		// TODO Auto-generated method stub
		setId(user.getId());
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setEmail(user.getEmail());
		setRole(user.getProfile().getRole().toString());
		
		return this;
	}
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + id + " " + firstName + " " 
				+ lastName + " " + email + " " 
				+ role + "]";
	}

}
