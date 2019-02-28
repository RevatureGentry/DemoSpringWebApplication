package com.revature.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

@Validated
public class RegistrationForm {

	private String username;
	private String password;
	private String passwordAgain;
	private String firstname;
	private String lastname;
	private String email;
	private String street;
	private String city;
	private State state;
	private String zipCode;
	private String phoneNumber;
	private PhoneType phoneType;

	public RegistrationForm() {
		this.username = "";
		this.password = "";
		this.passwordAgain = "";
		this.firstname = "";
		this.lastname = "";
		this.email = "";
		this.street = "";
		this.city = "";
		this.zipCode = "";
		this.phoneNumber = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, email, firstname, lastname, password, passwordAgain, phoneNumber, phoneType, state,
				street, username, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RegistrationForm)) {
			return false;
		}
		RegistrationForm other = (RegistrationForm) obj;
		return Objects.equals(city, other.city) && Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(password, other.password) && Objects.equals(passwordAgain, other.passwordAgain)
				&& Objects.equals(phoneNumber, other.phoneNumber) && phoneType == other.phoneType
				&& state == other.state && Objects.equals(street, other.street)
				&& Objects.equals(username, other.username) && Objects.equals(zipCode, other.zipCode);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistrationForm [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordAgain=");
		builder.append(passwordAgain);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", email=");
		builder.append(email);
		builder.append(", street=");
		builder.append(street);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", phoneType=");
		builder.append(phoneType);
		builder.append("]");
		return builder.toString();
	}

}
