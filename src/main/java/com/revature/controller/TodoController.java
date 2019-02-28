package com.revature.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.model.Todo;
import com.revature.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoService;

	@GetMapping(value="/todos")
	public String loadHomePage(Principal principal, Model model) {
		model.addAttribute("currentUser", principal.getName());
		model.addAttribute("todos", todoService.getAllTodos(principal));
		return "home";
	}
	
	@GetMapping(value="/todos/create")
	public String loadCreateTodoPage(Principal principal, Model model) {
		model.addAttribute("currentUser", principal.getName());
		return "todo";
	}
	
	@PostMapping(value="/todos", consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String createTodo(@ModelAttribute("todo") Todo todo, Model model, Principal principal) {
		final Todo newTodo = todoService.createTodo(todo, principal);
		if (newTodo == null)
			throw new RuntimeException("Failed to create todo");
		model.addAttribute("updateMessage", "Todo created successfully!");
		model.addAttribute("currentUser", principal.getName());
		model.addAttribute("todos", todoService.getAllTodos(principal));
		return "home";
	}
	
	@PutMapping(value="/todos", consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String updateTodos(@RequestParam("completedTodo") Integer todoId, Model model, Principal principal) {
		final String message = todoService.completeTodo(todoId);
		model.addAttribute("updateMessage", message);
		model.addAttribute("currentUser", principal.getName());
		model.addAttribute("todos", todoService.getAllTodos(principal));
		return "home";
	}
	
	@DeleteMapping(value="/todos", consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String deleteTodo(@RequestParam("deletedTodo") int id, Principal principal, Model model) {
		final String message = todoService.deleteTodo(id);
		model.addAttribute("updateMessage", message);
		model.addAttribute("currentUser", principal.getName());
		model.addAttribute("todos", todoService.getAllTodos(principal));
		return "home";
	}
	
	@ModelAttribute("todo")
	public Todo todoModelAttribute() {
		return new Todo();
	}
}
