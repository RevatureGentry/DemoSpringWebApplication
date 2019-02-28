package com.revature.service;

import java.security.Principal;

import com.revature.model.UserInformation;

public interface UserInfoService {

	UserInformation getUserInformation(Principal principal);
	UserInformation updateUserInformation(UserInformation info, Principal principal);
}
