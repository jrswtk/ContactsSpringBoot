package com.user.reg.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.user.reg.domain.Contact;
import com.user.reg.domain.ContactJson;
import com.user.reg.domain.ContactType;
import com.user.reg.domain.User;
import com.user.reg.exception.UserNotFoundException;
import com.user.reg.service.ContactService;
import com.user.reg.service.UserService;
import com.user.reg.utils.ImageEncoderUtil;
import com.user.reg.utils.JsonConverterUtil;

@RestController
@RequestMapping("/rest")
public class ContactsRestController {
	
	@Autowired
	private ContactService contactService;

	@Autowired
	private UserService userService;

	@Autowired
	private JsonConverterUtil<Contact, ContactJson> contactConverter;
	
	@RequestMapping(value = "/contact/add", method = RequestMethod.PUT)
	public void addContact(@RequestBody ContactJson contactJson) throws UserNotFoundException {
		Contact contact = contactCreate(contactJson);
		
		
		contactService.addContact(contact);
	}
	
	@RequestMapping(value = "/contact/update", method = RequestMethod.PUT)
	public void updateContact(@RequestBody ContactJson contactJson) throws UserNotFoundException {		
		Contact contact = contactUpdate(contactJson);		
		contactService.updateContact(contact);
	}

	@RequestMapping(value = "/contact/delete/{contactId}", method = RequestMethod.POST)
	public void deleteContact(@PathVariable Long contactId) {
		contactService.deleteContact(contactService.findById(contactId).getId());
	}
	
	@RequestMapping(value = "/contact/contactypes", method = RequestMethod.GET)
	public ContactType[] getContactTypes() {
		return ContactType.values();
	}
	
	@RequestMapping(value = "/contact/contacts", method = RequestMethod.GET)
	public List<ContactJson> getContactsByUserId() throws UserNotFoundException {
		List<ContactJson> contacts = null;
		
		User user = userService.findByEmail(getCurrentUserEmail());
				
		if(user != null) {
			long userId = user.getId();
			
			try {
				contacts = getContacts(contacts, userId);
			} catch (InstantiationException 
					| IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return contacts;
	}

	@RequestMapping(value = "/contact/get/{userId}", method = RequestMethod.GET)
	public List<ContactJson> getContactsByUserId(@PathVariable Long userId) {
		List<ContactJson> contacts = null;

		try {
			contacts = getContacts(contacts, userId);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contacts;
	}
	            
    private String getCurrentUserEmail() {
    	Authentication auth = SecurityContextHolder
    			.getContext()
    			.getAuthentication();
    	
    	return auth.getName();
    }
    
    private List<ContactJson> getContacts(List<ContactJson> contacts, long userId) 
    		throws InstantiationException, IllegalAccessException{
    	
    	contacts = contactConverter.convertToJsons(
					contactService.getContactsByUserId(userId),
					ContactJson.class);
    	
    	return contacts;
    }
    
    private Contact contactUpdate(ContactJson contactJson) throws UserNotFoundException {
		Contact contact = contactService.findById(
				contactJson.getId());
		setContact(true, contact, contactJson);

		return contact;
	}

	private Contact contactCreate(ContactJson contactJson) throws UserNotFoundException {
		Contact contact = new Contact();		
		setContact(false, contact, contactJson);
		
		return contact;
	}
	
	private void setContact(boolean update, Contact contact, ContactJson contactJson) throws UserNotFoundException {
		
		Logger.getLogger(ContactsRestController.class).info(contactJson);
		
		contact.setFirstName(contactJson.getFirstName());
		contact.setLastName(contactJson.getLastName());
		contact.setPhoneNumber(contactJson.getPhoneNumber());
		contact.setEmail(contactJson.getEmail());
		contact.setContactType(contactJson.getContactType());
		
		if(contactJson.getImageEncode64() != null) {			
			
			try {
				byte[] imageBytes = ImageEncoderUtil.getImageByteArray(
						contactJson.getImageEncode64());
				contact.setImage(imageBytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(contact.getImage() != null) {
			contact.setImage(null);
		}
		
		if(!update) {
			User user = userService.findByEmail(getCurrentUserEmail());
			contact.setUser(user);
		}
	}
	
}
