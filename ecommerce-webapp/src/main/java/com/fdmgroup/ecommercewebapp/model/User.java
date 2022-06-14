package com.fdmgroup.ecommercewebapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fdmgroup.ecommercewebapp.enumeration.UserStatus;
import com.fdmgroup.ecommercewebapp.enumeration.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name = "userTable")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String email;
	private String password;
	private int bankAccountNo;
//	private UserStatus status;
//	private UserType userType;

	public User() {
	}

	
	public User(String email, String password, int bankAccountNo) {
		this.email = email;
		this.password = password;
		this.bankAccountNo = bankAccountNo;
	}


	public User(int userId, String email, String password, int bankAccountNo) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.bankAccountNo = bankAccountNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(int bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", bankAccountNo="
				+ bankAccountNo + "]";
	}

}
