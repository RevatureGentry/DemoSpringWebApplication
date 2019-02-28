package com.revature.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

import com.revature.model.Todo;
import com.revature.model.UserInformation;
import com.revature.service.UserInfoService;

@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping(value="/info")
	public String getUserInformation(Principal principal, Model model) {
		UserInformation info = userInfoService.getUserInformation(principal);
		model.addAttribute("firstname", info.getFirstname());
		model.addAttribute("lastname", info.getLastname());
		model.addAttribute("email", info.getEmail());
		model.addAttribute("phoneNumber", info.getPhoneNumber());
		model.addAttribute("phoneType", info.getPhoneType());
		model.addAttribute("street", info.getStreet());
		model.addAttribute("city", info.getCity());
		model.addAttribute("state", info.getState());
		model.addAttribute("zipCode", info.getZipCode());
		return "info";
	}
	
	@GetMapping(value="/info/update")
	public String getUserInformationUpdateForm(Principal principal, Model model) {
		model.addAttribute("userInfo", userInfoService.getUserInformation(principal));
		return "info-update";
	}
	
	@PutMapping(value="/info")
	public String updateUserInformation(@ModelAttribute("userInfoModel") UserInformation info, Principal principal, Model model) {
		UserInformation updated = userInfoService.updateUserInformation(info, principal);
		model.addAttribute("userInfo", updated);
		return "info-update";
	}
	
	@ModelAttribute("todo")
	public Todo todoModelAttribute() {
		return new Todo();
	}
	
	@ModelAttribute("userInfoModel")
	public UserInformation userInformationModelAttribute() {
		return new UserInformation();
	}
	
	
}
