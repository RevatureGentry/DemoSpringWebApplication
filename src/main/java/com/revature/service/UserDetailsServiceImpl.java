package com.revature.service;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.model.AppUser;
import com.revature.repository.UserRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository repo;
	
	public UserDetailsServiceImpl(final UserRepository repo) {
		this.repo = repo;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> user = repo.findById(username);
		if (!user.isPresent())
			throw new BadCredentialsException("Failed to authenticate: Bad Credentials");
		return user.get();
	}
	
	
}
