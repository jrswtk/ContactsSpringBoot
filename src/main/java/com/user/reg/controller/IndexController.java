package com.user.reg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class IndexController {

	@RequestMapping("/")
	public String getIndex(@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "confirm", required = false) String confirm,
			@RequestParam(value = "unsuccess", required = false) String unsuccess,
			@RequestParam(value = "userWasDeleted", required = false) String deleted,
			@RequestParam(value = "accountIsActive", required = false) String actived,
			@RequestParam(value = "confirmAgain", required = false) String confirmAgain,
			@RequestParam(value = "userNotFound", required = false) String userNotFound,
			Model model) {
		
		if (success != null) {
			model.addAttribute("message", "Zostales zarejestrowany.");
		}
		
		if (confirm != null) {
			model.addAttribute("error", "Konto wymaga potwierdzenia.");
		}
		
		if (unsuccess != null) {
			model.addAttribute("error", "Niepowodzenie przy weryfikacji konta.");
		}
		
		if (deleted != null) {
			model.addAttribute("error", "Uzytkownik zostal usuniety przez administratiora.");
		}
		
		if (actived != null) {
			model.addAttribute("error", "Konto zostalo wczesniej aktywowane.");
		}
		
		if (confirmAgain != null) {
			model.addAttribute("error", "Link aktywacyjny jest nieaktualny.\n"
					+ "Nowy link zostal wyslany na Twoj adres email.");
		}
		
		if (userNotFound != null) {
			model.addAttribute("error", "Uzytkownik nie istnieje.");
		}
		
		return "index";
	}
	
}
