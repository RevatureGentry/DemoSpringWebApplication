package com.revature.service;

import java.security.Principal;
import java.util.List;

import com.revature.model.Todo;

public interface TodoService {

	List<Todo> getAllTodos(Principal principal);
	Todo createTodo(Todo todo, Principal principal);
	String deleteTodo(int id);
	String completeTodo(int id);
	String completeTodos(List<Integer> todoIds);
}
