package com.revature.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.model.AppUser;
import com.revature.model.ApplicationAuthority;
import com.revature.model.RegistrationForm;
import com.revature.model.UserInformation;
import com.revature.repository.UserInfoRepository;
import com.revature.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final UserRepository repo;
	private final UserInfoRepository infoRepo;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public RegistrationServiceImpl(final UserRepository repo, final UserInfoRepository infoRepo, final PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.infoRepo = infoRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public AppUser attemptRegisterUser(RegistrationForm form) {
		AppUser user = new AppUser();
		user.setUsername(form.getUsername());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setAuthorities(Arrays.asList(new ApplicationAuthority("ROLE_USER")));
		
		UserInformation info = new UserInformation();
		info.setFirstname(form.getFirstname());
		info.setLastname(form.getLastname());
		info.setEmail(form.getEmail());
		info.setPhoneNumber(form.getPhoneNumber());
		info.setPhoneType(form.getPhoneType());
		info.setCity(form.getCity());
		info.setStreet(form.getStreet());
		info.setState(form.getState());
		info.setZipCode(form.getZipCode());
		
		info.setUser(user);
		user.setUserInformation(info);
		repo.save(user);
		infoRepo.save(info);
		return user;
	}

}
