package com.revature.service;

import java.security.Principal;
import java.util.List;

import com.revature.model.Todo;

public interface TodoService {

	List<Todo> getAllTodos(Principal principal);
	Todo createTodo(Todo todo, Principal principal);
	String deleteTodo(int id, Principal principal);
	String completeTodo(int id, Principal principal);
	String completeTodos(List<Integer> todoIds);
}
