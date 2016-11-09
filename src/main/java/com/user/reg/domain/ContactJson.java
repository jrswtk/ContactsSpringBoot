package com.user.reg.domain;

import com.user.reg.utils.JsonUtil;

public class ContactJson implements JsonUtil<Contact, ContactJson>{
	
	private long id;
	private long ownerUserId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private ContactType contactType;
	private String imageEncode64;
	private byte[] bytesImage;

	public ContactJson() {
		// TODO Auto-generated constructor stub
		this.contactType = ContactType.OTHER;
	}
	
	@Override
	public ContactJson convertToJson(Contact contact) {
		// TODO Auto-generated method stub
		setId(contact.getId());
		setOwnerUserId(contact.getUser().getId());
		setFirstName(contact.getFirstName());
		setLastName(contact.getLastName());
		setPhoneNumber(contact.getPhoneNumber());
		setEmail(contact.getEmail());
		setContactType(contact.getContactType());
		setBytesImage(contact.getImage());
		
		return this;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getOwnerUserId() {
		return ownerUserId;
	}
	
	public void setOwnerUserId(long ownerUserId) {
		this.ownerUserId = ownerUserId;
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

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
	
	public String getImageEncode64() {
		return imageEncode64;
	}
	
	public void setImageEncode64(String imageEncode64) {
		this.imageEncode64 = imageEncode64;
	}
	
	public void setBytesImage(byte[] bytesImage) {
		this.bytesImage = bytesImage;
	}
	
	public byte[] getBytesImage() {
		return bytesImage;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + id + " " + ownerUserId + " "
				+ firstName + " " + lastName + " " 
				+ phoneNumber + " " + email + " " 
				+ contactType + " " + imageEncode64 + " " 
				+ bytesImage + "]";
	}

}
