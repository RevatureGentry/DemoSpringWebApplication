package com.revature.model;

import org.springframework.security.core.userdetails.UserDetails;

public interface ApplicationUserDetails extends UserDetails {

	String getEmail();
}
