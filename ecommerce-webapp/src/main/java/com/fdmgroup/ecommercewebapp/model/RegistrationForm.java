package com.fdmgroup.ecommercewebapp.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component

public class RegistrationForm {

	private String email;
	private String password;
	private String confirmPassword;
	private int bankAccountNo;
	
	
	public RegistrationForm() {
	}
	public RegistrationForm(String email, String password, int bankAccountNo) {
		this.email = email;
		this.password = password;
		this.bankAccountNo = bankAccountNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public int getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(int bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	@Override
	public String toString() {
		return "RegistrationForm [email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", bankAccountNo=" + bankAccountNo + "]";
	}
	
	
}
