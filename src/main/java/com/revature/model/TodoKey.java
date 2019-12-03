package com.revature.model;

import java.util.Objects;

/**
 * @author William Gentry
 */
public class TodoKey implements Comparable<TodoKey> {

	private final Long todoId;

	public TodoKey(Long todoId) {
		this.todoId = todoId;
	}

	@Override
	public int compareTo(TodoKey o) {
		return 0;
	}

	public Long getKey() {
		return todoId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TodoKey todoKey = (TodoKey) o;
		return Objects.equals(todoId, todoKey.todoId);
	}

	@Override
	public int hashCode() {
		return 1;
	}
}
