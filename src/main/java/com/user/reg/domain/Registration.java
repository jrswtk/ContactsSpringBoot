package com.user.reg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;

import com.user.reg.form.AccountForm;

@Entity
public class Registration {

	@Id
	@GeneratedValue
	private long id;
	
	@Email
	@Column(unique = true)
	private String email;
	private String token;
	
	public Registration() {
		// TODO Auto-generated constructor stub
	}
	
	public Registration(AccountForm accountForm, String token) {
		email = accountForm.getEmail();
		this.token = token;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + id + " " + email + " " + token + "]";
	}
	
}
