package com.user.reg.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.reg.domain.Contact;
import com.user.reg.repository.ContactRepository;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public Contact addContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactRepository.save(contact);
	}

	@Override
	public Contact findById(long id) {
		// TODO Auto-generated method stub
		return contactRepository.findOne(id);
	}

	@Override
	public Contact findByEmail(String email) {
		// TODO Auto-generated method stub
		return contactRepository.findOneByEmail(email);
	}

	@Override
	public Contact updateContact(Contact contact) {
		// TODO Auto-generated method stub
		Contact findContact = contactRepository.findOne(
				contact.getId());
		updateContactAttributes(contact, findContact);
		contactRepository.save(findContact);
		return findContact;
	}

	@Override
	public void deleteContact(long id) {
		// TODO Auto-generated method stub
		contactRepository.delete(id);
	}

	@Override
	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return (List<Contact>) contactRepository.findAll();
	}
	
	@Override
	public List<Contact> getContactsByUserId(long id) {
		// TODO Auto-generated method stub
		return (List<Contact>) contactRepository.findContactsByUserId(id);
	}
	
	private void updateContactAttributes(Contact contact, Contact contactToUpdate) {
		contactToUpdate.setFirstName(contact.getFirstName());
	}

}
