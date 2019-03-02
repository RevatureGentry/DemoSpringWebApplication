package com.revature.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.revature.service.DarkModeService;
import com.revature.service.DarkModeServiceImpl;
import com.revature.service.TodoService;

@Controller
public class DarkModeController {
	
	private DarkModeService darkModeService;
	
	@Autowired
	private TodoService todoService;

	@GetMapping(value="/enable-dark-mode")
	public String enableDarkMode(Principal principal, Model model) {
		darkModeService = new DarkModeServiceImpl();
		darkModeService.enableDarkMode();
		model.addAttribute("currentUser", principal.getName());
		model.addAttribute("todos", todoService.getAllTodos(principal));
		return "redirect:/todos";
	}
}
