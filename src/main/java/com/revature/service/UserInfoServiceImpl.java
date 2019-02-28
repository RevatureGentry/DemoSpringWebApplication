package com.revature.service;

import java.security.Principal;

import org.springframework.stereotype.Service;

import com.revature.model.AppUser;
import com.revature.model.UserInformation;
import com.revature.repository.UserInfoRepository;
import com.revature.repository.UserRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	private final UserInfoRepository repo;
	private final UserRepository userRepo;
	
	public UserInfoServiceImpl(final UserInfoRepository repo, final UserRepository userRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
	}
	
	@Override
	public UserInformation getUserInformation(Principal principal) {
		return repo.findUserInformationByUser(new AppUser(principal.getName()));
	}

	@Override
	public UserInformation updateUserInformation(UserInformation info, Principal principal) {
		AppUser user = userRepo.getOne(principal.getName());
		user.getUserInformation().setFirstname(info.getFirstname());
		user.getUserInformation().setLastname(info.getLastname());
		user.getUserInformation().setEmail(info.getEmail());
		user.getUserInformation().setPhoneNumber(info.getPhoneNumber());
		user.getUserInformation().setPhoneType(info.getPhoneType());
		user.getUserInformation().setStreet(info.getStreet());
		user.getUserInformation().setState(info.getState());
		user.getUserInformation().setCity(info.getCity());
		user.getUserInformation().setZipCode(info.getZipCode());
		userRepo.save(user);
		return info;
	}
}
