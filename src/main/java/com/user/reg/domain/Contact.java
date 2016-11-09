package com.user.reg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Email;

@Entity
public class Contact {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "contact_type")
	private ContactType contactType;
	
	private byte[] image;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Contact() {
		// TODO Auto-generated constructor stub
		this.contactType = ContactType.OTHER;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public ContactType getContactType() {
		return contactType;
	}
	
	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + firstName + " " + lastName + " " 
				+ phoneNumber + " " + email + " " 
				+ contactType + "]";
	}
	
}
