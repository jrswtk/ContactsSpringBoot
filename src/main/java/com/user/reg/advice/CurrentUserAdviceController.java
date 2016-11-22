package com.user.reg.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.user.reg.exception.UserNotFoundException;
import com.user.reg.security.CurrentUser;
import com.user.reg.security.RoleEnum;

@ControllerAdvice
public class CurrentUserAdviceController {

	@ModelAttribute("currentUser")
	public CurrentUser getCurrentUser(@AuthenticationPrincipal User user) {

		CurrentUser currentUser = null;

		if (user != null) {
			currentUser = CurrentUser.getCurretnUser();
			currentUser.setName(user.getUsername());
		}

		return currentUser;
	}

	@ModelAttribute("roleEnum")
	public CurrentUser getRole(@AuthenticationPrincipal User user) {
		CurrentUser currentUser = null;

		if (user != null) {
			currentUser = CurrentUser.getCurretnUser();
			List<GrantedAuthority> auths = null;
			auths = new ArrayList<>(user.getAuthorities());

			for (GrantedAuthority ga : auths) {
				if (ga.getAuthority().equals(RoleEnum.ADMIN.toString())) {
					currentUser.setRole(RoleEnum.ADMIN);
					return currentUser;
				} else if (ga.getAuthority().equals(RoleEnum.USER.toString())) {
					currentUser.setRole(RoleEnum.USER);
				}
			}
			
		}

		return currentUser;
	}

	@ExceptionHandler(value = {UserNotFoundException.class})
	public String handleNotFoundExceptions() {
		SecurityContextHolder.clearContext();
		return "redirect:?userNotFound";
	}
	
	@ExceptionHandler(value = {InternalAuthenticationServiceException.class})
	public void handleInvalidUser() {
	}

}
