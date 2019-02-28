package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "app_authority")
public class ApplicationAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "authority")
	private String authority;

	@ManyToOne
	@JoinColumn(name = "username")
	private AppUser user;
	
	public ApplicationAuthority() {}
	
	public ApplicationAuthority(String authority) {
		this.authority = authority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authority, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ApplicationAuthority)) {
			return false;
		}
		ApplicationAuthority other = (ApplicationAuthority) obj;
		return Objects.equals(authority, other.authority) && id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicationAuthority [authority=");
		builder.append(authority);
		builder.append("]");
		return builder.toString();
	}

}
