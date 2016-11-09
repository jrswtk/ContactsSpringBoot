package com.user.reg.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.user.reg.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {

	Contact findOneByEmail(String email);
	List<Contact> findContactsByUserId(long userId);
}
