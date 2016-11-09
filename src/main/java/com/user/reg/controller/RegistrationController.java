package com.user.reg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.reg.domain.Profile;
import com.user.reg.domain.Registration;
import com.user.reg.domain.User;
import com.user.reg.exception.RegistrationNotFoundException;
import com.user.reg.exception.UserNotFoundException;
import com.user.reg.form.AccountForm;
import com.user.reg.security.RoleEnum;
import com.user.reg.service.EmailService;
import com.user.reg.service.ProfileService;
import com.user.reg.service.RegistrationServiceImpl;
import com.user.reg.service.TokenService;
import com.user.reg.service.UserService;
import com.user.reg.utils.PasswordUtil;
import com.user.reg.validation.AccountFormValidator;

@Controller
@RequestMapping
public class RegistrationController {
	
	private Logger logger = Logger.getLogger(RegistrationController.class);

	@Autowired
	private ProfileService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationServiceImpl registrationService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AccountFormValidator accountFormValidator;
	
	@InitBinder("accountForm")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(accountFormValidator);
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegisterPage(@ModelAttribute("accountForm") AccountForm accountForm) {
		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerAccount(@ModelAttribute("accountForm") @Valid AccountForm accountForm,
			BindingResult result, HttpServletRequest request) {
	
		logger.info(accountForm);
		
		if(result.hasErrors()) {
			return "registration";
		}
		
		User user = createUser(accountForm);
		userService.addUser(user);
		
		String token = tokenService.generateToken();
		Registration registration = new Registration(accountForm, token);
		registrationService.addRegistration(registration);

		sendConfirmEmail(registration, request);
		
		return "redirect:/?confirm";
	}
	
	@RequestMapping("/confirm")
	public String confirmUser(@RequestParam String email, @RequestParam String token,
			HttpServletRequest request) throws UserNotFoundException {
		
		Registration registration = null;
		
		try {
			registration = registrationService.findByEmail(email);
		} catch (RegistrationNotFoundException e) {
			// TODO Auto-generated catch block
			if(userWasDeleted(email)) {
				return "redirect:/?userWasDeleted";
			} else if(userIsActive(email)) {
				return "redirect:/?accountIsActive";
			} else {
				Registration newRegistration = 
						getNewRegistration(email);
				sendConfirmEmail(newRegistration, request);
				
				return "redirect:/?confirmAgain";
			}
			
		}
				
		if(!registration.getToken().equals(token)) {
			return "redirect:/?unsuccess";
		}
		
		registrationService.deleteRegistration(registration.getId());
		User user = userService.findByEmail(email);
		user.setConfirmed(true);
		userService.updateUser(user);
		
		return "redirect:/?success";
	}
	
	private Registration getNewRegistration(String email) {
		Registration registration = new Registration();
		registration.setEmail(email);
		registration.setToken(tokenService.generateToken());
		registrationService.addRegistration(registration);
				
		return registration;
	}
	
	private boolean userIsActive(String email) throws UserNotFoundException {
		User user = userService.findByEmail(email);
		return user != null 
				? user.isConfirmed()
				: false;
	}
	
	private boolean userWasDeleted(String email) throws UserNotFoundException {
		return userService.findByEmail(email) 
				== null;
	}
	
	private void sendConfirmEmail(Registration registration, HttpServletRequest request) {
		
		String baseURL = getBaseURL(request);
		emailService.sendConfirmationMail(registration, baseURL);
	}
	
	private String getBaseURL(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		String baseURL = requestURL.substring(0, requestURL
				.indexOf(request.getContextPath()) 
					+ request.getContextPath().length());
				
		return baseURL;
	}
				
	private User createUser(AccountForm accountForm) {
		User user = new User();
		user.setFirstName(accountForm.getFirstName());
		user.setLastName(accountForm.getLastName());
		user.setEmail(accountForm.getEmail());
		user.setPass(getEncryptPassword(accountForm.getPass()));
		user.setProfile(getRole(accountForm.getRoleEnum()));
		
		return user;
	}
	
	private String getEncryptPassword(String password) {
		return passwordUtil.getEncryptPassword(password);
	}
	
	private Profile getRole(RoleEnum roleEnum) {
		return roleService.findProfileByRole(roleEnum);
	}
	
}
