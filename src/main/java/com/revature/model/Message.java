package com.revature.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

	@Column(name = "content")
	private String content;

	@ManyToOne
	private AppUser createdBy;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	public Message() {}

	public Message(String content, AppUser createdBy, LocalDateTime timestamp) {
		this.content = content;
		this.createdBy = createdBy;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AppUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AppUser createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Message message = (Message) o;
		return id == message.id &&
						Objects.equals(content, message.content) &&
						Objects.equals(timestamp, message.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, timestamp);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
						.add("id=" + id)
						.add("content='" + content + "'")
						.add("timestamp=" + timestamp)
						.toString();
	}
}
