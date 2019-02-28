package com.revature.service;

import com.revature.model.AppUser;
import com.revature.model.RegistrationForm;

public interface RegistrationService {

	AppUser attemptRegisterUser(RegistrationForm form);
}
