package com.revature.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.AppUser;
import com.revature.model.Todo;
import com.revature.repository.TodoRepository;
import com.revature.repository.UserRepository;
import com.revature.service.exception.TodoNotFoundException;

@Service
public class TodoServiceImpl implements TodoService {

	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
	
	@Autowired
	private TodoRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	protected ThreadLocal<AtomicInteger> counter = ThreadLocal.withInitial(() -> new AtomicInteger(1));

	@Override
	public List<Todo> getAllTodos(Principal principal) {
		return getTodosByUserAsync(repo.findAll(), principal);
	}

	@Override
	public Todo createTodo(Todo todo, Principal principal) {
		AppUser user = userRepo.getOne(principal.getName());
		user.getTodos().add(todo);
		todo.setUser(user);
		return repo.save(todo);
	}

	@Override
	public String deleteTodo(int id, Principal principal) {
		List<Todo> todos = new CopyOnWriteArrayList<>(getTodosByUserAsync(repo.findAll(), principal));
		try {
			for (Todo todo : todos) {
				if (todo.getId() == id)
					repo.delete(todo);
			}
			return "Todo successfully removed";
		} catch (IllegalArgumentException e) {
			logger.error("Failed to remove todo: {}", e);
			return "Failed to remove todo. Check logs";
		}
	}

	@Override
	public String completeTodo(int id, Principal principal) {
		List<Todo> todos = getTodosByUserAsync(repo.findAll(), principal);
		for (Todo todo : todos) {
			if (todo.getId() == id)
				todo.setCompleted(true);
			repo.saveAll(todos);
		}
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
	
	private List<Todo> getTodosByUserAsync(List<Todo> todos, Principal principal) {
		List<Todo> currentUserTodos = new CopyOnWriteArrayList<>(todos);
		ExecutorService service = null;
		try {
			do {
				service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
				
				Future<Todo> future = service.submit(new FetchCurrentUserTodoTask(this, currentUserTodos, principal));
				currentUserTodos.remove(future.get());
			} while (!listContainsOnlyCurrentUserTodos(currentUserTodos, principal));
		} catch (TodoNotFoundException e) {
			
		} catch (InterruptedException | ExecutionException e) {
			
		}
		
		finally {
			service.shutdown();
		}
		
		Collections.sort(currentUserTodos, (t1, t2) -> t1.getId() - t1.getId());
		return currentUserTodos.parallelStream().filter(todo -> todo.getUser().getUsername().equals(principal.getName())).collect(Collectors.toList());
	}
	
	private boolean listContainsOnlyCurrentUserTodos(List<Todo> todos, Principal principal) {
		for (Todo t : todos) {
			if (!t.getUser().getUsername().equals(principal.getName()))
				return false;
		}
		return true;
	}

	private class FetchCurrentUserTodoTask implements Callable<Todo> {

		private final TodoServiceImpl todoService;
		private List<Todo> todos;
		private Principal principal;
		
		
		private FetchCurrentUserTodoTask(TodoServiceImpl todoService, List<Todo> todos, Principal principal) {
			this.todoService = todoService;
			this.todos = todos;
			this.principal = principal;
		}
		
		@Override
		public Todo call() throws Exception {
			for (Todo t : todos) {
				this.todoService.counter.set(new AtomicInteger(todoService.counter.get().incrementAndGet()));
				if (!t.getUser().getUsername().equals(principal.getName())) {
					return t;
				}
			}
			throw new TodoNotFoundException();
		}
		
	}
}
