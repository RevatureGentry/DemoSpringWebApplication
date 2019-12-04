package com.revature.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.revature.model.RegistrationForm;
import com.revature.model.validator.RegistrationFormValidator;
import com.revature.repository.UserRepository;
import com.revature.service.RegistrationService;

@Controller
public class RegistrationController {
	
	private final UserRepository repo;
	private final RegistrationService service;
	
	@Autowired
	public RegistrationController(final UserRepository repo, final RegistrationService service) {
		this.repo = repo;
		this.service = service;
	}

	@InitBinder
	public void registrationFormValidator(WebDataBinder binder) {
		binder.addValidators(new RegistrationFormValidator(this.repo));
	}
	
	@GetMapping(value="/register")
	public String showRegistrationForm() {
		return "register";
	}
	
	@PostMapping(value="/register")
	public String attemptRegistration(@Valid @ModelAttribute("registrationForm") final RegistrationForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "register";
		}
		if (service.attemptRegisterUser(form) != null) { 
			model.addAttribute("registrationSuccessMessage", form.getUsername() + " successfully registered!");
			return "login";
		}
		return "register";
	}
	
	@ModelAttribute("registrationForm")
	public RegistrationForm getRegistrationFormModelAttribute() {
		return new RegistrationForm();
	}
}
