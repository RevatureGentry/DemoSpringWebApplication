package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "body")
	private String body;

	@Column(name = "completed")
	private boolean completed;

	@ManyToOne
	@JoinColumn(name = "username")
	private AppUser user;

	public Todo() {
	}
	
	public Todo(int id) {
		this.id = id;
	}

	public Todo(String title, String body, boolean completed, AppUser user) {
		super();
		this.title = title;
		this.body = body;
		this.completed = completed;
		this.user = user;
	}

	public Todo(int id, String title, String body, boolean completed) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, completed, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Todo)) {
			return false;
		}
		Todo other = (Todo) obj;
		return Objects.equals(body, other.body) && completed == other.completed && id == other.id
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Todo [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", body=");
		builder.append(body);
		builder.append(", completed=");
		builder.append(completed);
		builder.append("]");
		return builder.toString();
	}

}
