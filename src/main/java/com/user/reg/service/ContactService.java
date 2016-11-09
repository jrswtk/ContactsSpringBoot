package com.user.reg.service;

import java.util.List;

import com.user.reg.domain.Contact;

public interface ContactService {
		
	Contact addContact(Contact contact);
	Contact findById(long id);
	Contact findByEmail(String email);
	Contact updateContact(Contact contact);
	void deleteContact(long id);
	List<Contact> findAll();
	List<Contact> getContactsByUserId(long userId);

}
