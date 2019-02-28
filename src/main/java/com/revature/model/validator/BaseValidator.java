package com.revature.model.validator;

import java.util.regex.Pattern;

public abstract class BaseValidator {

	protected final Pattern validTextOnlyPattern = Pattern.compile("^[a-zA-Z]+$");
	protected final Pattern validStreetAddressPattern = Pattern.compile("^[a-zA-Z0-9\\s]+$");
	protected final Pattern validNumericPattern = Pattern.compile("^[0-9]+$");
	protected final Pattern validEmail = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	protected final Pattern validUsername = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()]+$");
	protected final Pattern validPassword = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$!%^&+=]).*");
	protected final Pattern validPhonePattern = Pattern.compile("(?:\\d{3}-){2}\\d{4}");
	
	protected final boolean isPhoneNumberValid(String phoneNumber) {
		return validPhonePattern.matcher(phoneNumber).matches();
	}
	
	protected final boolean isEmailValid(String email) {
		return validEmail.matcher(email).matches();
	}
	
	protected final boolean isUsernameValid(String username) {
		return validUsername.matcher(username).matches();
	}

	protected final boolean isPasswordValid(String password) {
		return validPassword.matcher(password).matches();
	}
	
	protected final boolean passwordContainsUpper(String password) {
		return Pattern.matches("(?=.*[A-Z]).*", password);
	}
	
	protected final boolean passwordContainsLower(String password) {
		return Pattern.matches("(?=.*[a-z]).*", password);
	}
	
	protected final boolean passwordContainsValidSpecial(String password) {
		return Pattern.matches("(?=.*[@#$!%^&+=]).*", password);
	}
	
	protected final boolean passwordContainsNumber(String password) {
		return Pattern.matches("(?=.*[0-9]).*", password);
	}
}
