package com.user.reg.security;

public class CurrentUser {

	private String name;
	private RoleEnum role;
	
	private static CurrentUser currentUser;
	
	public static CurrentUser getCurretnUser() {
		if(currentUser == null) {
			currentUser = new CurrentUser();
		}
		return currentUser;
	}
	
	private CurrentUser() {
		// TODO Auto-generated constructor stub
	}
	
	public RoleEnum getRole() {
		return role;
	}
	
	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
