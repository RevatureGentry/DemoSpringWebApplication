package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.AppUser;
import com.revature.model.Todo;

@Repository
@Transactional
public interface TodoRepository extends JpaRepository<Todo, Integer> {

	@Transactional(readOnly=true)
	List<Todo> findAllTodosByUser(AppUser user);
	
	@Transactional(readOnly=true)
	List<Todo> findAllTodosByUserOrderByIdDesc(AppUser user);
}
