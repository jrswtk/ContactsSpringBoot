package com.user.reg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.reg.domain.User;
import com.user.reg.exception.UserNotFoundException;
import com.user.reg.service.UserService;

@Controller
public class ContactsController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/contacts")
	public String getContactsPage(Model model) throws UserNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailAsUserName = auth.getName();
		
		User user = userService.findByEmail(emailAsUserName);
		model.addAttribute("userId", user.getId());
		
		return "contacts";
	}
	
}
