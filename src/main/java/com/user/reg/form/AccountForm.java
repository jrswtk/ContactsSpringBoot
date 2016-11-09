package com.user.reg.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.user.reg.security.RoleEnum;

public class AccountForm {

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String pass;
	@NotEmpty
	private String repeatPass;
	private RoleEnum roleEnum;

	public AccountForm() {
		// TODO Auto-generated constructor stub
		this.roleEnum = RoleEnum.USER;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRepeatPass() {
		return repeatPass;
	}

	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;
	}

	public RoleEnum getRoleEnum() {
		return roleEnum;
	}
	
	public void setRoleEnum(RoleEnum roleEnum) {
		this.roleEnum = roleEnum;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "AccountForm: [" 
				+ firstName + " " 
				+ lastName + " " 
				+ email + " " 
				+ pass + " " 
				+ repeatPass + " " 
				+ roleEnum + "]";
	}

}
