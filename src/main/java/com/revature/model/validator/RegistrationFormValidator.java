package com.revature.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.revature.model.PhoneType;
import com.revature.model.RegistrationForm;
import com.revature.model.State;
import com.revature.repository.UserRepository;

public class RegistrationFormValidator extends BaseValidator implements Validator {

	private final UserRepository repo;
	
	public RegistrationFormValidator(final UserRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Verify that all fields are not empty
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "passwordAgain.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "street.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "state.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "zipCode.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "phoneNumber.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneType", "phoneType.empty");
		
		final RegistrationForm form = (RegistrationForm) target;
		
		/*
		 * Verify that username does not already exist
		 */
		if (repo.existsById(form.getUsername()))
			errors.rejectValue("username","username.alreadyExists");
		
		/*
		 * Passwords Match Validation
		 */
		if (!form.getPassword().equals(form.getPasswordAgain()))
			errors.rejectValue("password", "password.mismatch");
		
		/*
		 * Username Length Validation
		 */
		if (form.getUsername().length() < 5) {
			errors.rejectValue("username", "username.min");
		} else if (form.getUsername().length() > 30) {
			errors.rejectValue("username", "username.max");
		}
		
		/*
		 * Username Content Validation
		 */
		if (!isUsernameValid(form.getUsername())) 
			errors.rejectValue("username", "username.invalid");
		
		/*
		 * Password Length Validation
		 */
		if (form.getPassword().length() < 8)
			errors.rejectValue("password", "registrationForm.password.Min");
		if (form.getPassword().length() > 30) 
			errors.rejectValue("password", "registrationForm.password.Max");
		
		/*
		 * Password Content Validation
		 */
		if (!isPasswordValid(form.getPassword())) {
			if (!passwordContainsUpper(form.getPassword())) {
				errors.rejectValue("password", "registrationForm.password.PasswordContainsUpper");
			} else if (!passwordContainsLower(form.getPassword())) {
				errors.rejectValue("password", "registrationForm.password.PasswordContainsLower");
			} else if (!passwordContainsValidSpecial(form.getPassword())) {
				errors.rejectValue("password", "registrationForm.password.PasswordContainsValidSpecial");
			} else if (!passwordContainsNumber(form.getPassword())) {
				errors.rejectValue("password", "registrationForm.password.PasswordContainsNumber");
			}
		}
		
		/*
		 * Email Address format validation
		 */
		if (!isEmailValid(form.getEmail())) 
			errors.rejectValue("email", "email.invalid");
		
		// Zip Code must be 5 digits
		if (!super.validNumericPattern.matcher(form.getZipCode()).matches() || form.getZipCode().length() > 5)
			errors.rejectValue("zipCode", "zipCode.invalid");

		// Street must contain valid characters
		if (!super.validStreetAddressPattern.matcher(form.getStreet()).matches())
			errors.rejectValue("street", "street.invalid");

		// Street must contain valid characters
		if (!super.validTextOnlyPattern.matcher(form.getCity()).matches())
			errors.rejectValue("city", "city.invalid");

		// Street must contain valid characters
		if (!(form.getState() instanceof State))
			errors.rejectValue("state", "state.invalid");
		
		/*
		 * Phone Validation
		 */
		if (!super.isPhoneNumberValid(form.getPhoneNumber()))
			errors.rejectValue("phoneNumber", "phoneNumber.invalid");
		
		if (!(form.getPhoneType() instanceof PhoneType))
			errors.rejectValue("phoneType", "phoneType.invalid");
	}

}
