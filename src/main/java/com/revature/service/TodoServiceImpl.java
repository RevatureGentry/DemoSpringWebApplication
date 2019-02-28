package com.revature.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.AppUser;
import com.revature.model.Todo;
import com.revature.repository.TodoRepository;
import com.revature.repository.UserRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
	
	@Autowired
	private TodoRepository repo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Todo> getAllTodos(Principal principal) {
		return repo.findAllTodosByUserOrderByIdDesc(new AppUser(principal.getName()));
	}

	@Override
	public Todo createTodo(Todo todo, Principal principal) {
		AppUser user = userRepo.getOne(principal.getName());
		user.getTodos().add(todo);
		todo.setUser(user);
		return repo.save(todo);
	}

	@Override
	public String deleteTodo(int id) {
		try {
			repo.delete(new Todo(id));
			return "Todo successfully removed";
		} catch (IllegalArgumentException e) {
			logger.error("Failed to remove todo: {}", e);
			return "Failed to remove todo. Check logs";
		}
	}

	@Override
	public String completeTodo(int id) {
		Todo todo = repo.getOne(id);
		todo.setCompleted(true);
		return "Todo completed!";
	}

	@Override
	public String completeTodos(List<Integer> todoIds) {
		List<Todo> todos = repo.findAll();
		List<Todo> completedTodos = new ArrayList<>();
		for (Todo todo : todos) {
			for (int id : todoIds) {
				if (todo.getId() == id) {
					completedTodos.add(todo);
				}
			}
		}
		
		for (Todo todo : completedTodos) {
			todo.setCompleted(true);
		}
		
		repo.saveAll(completedTodos);
		
		return completedTodos.size() + " updated successfully!";
	}
}
