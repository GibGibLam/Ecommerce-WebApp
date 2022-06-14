package com.fdmgroup.ecommercewebapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.ecommercewebapp.model.RegistrationForm;
import com.fdmgroup.ecommercewebapp.model.User;
import com.fdmgroup.ecommercewebapp.repository.IUserRepository;

@Service
public class AuthenticationService {
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	IUserRepository userRepo;

	public boolean authenticateUserSession(String sessionEmail) {
		Optional<User> foundUserOptional = userRepo.findByEmail(sessionEmail);
		return foundUserOptional.isPresent() ? true : false;
	}

	public boolean authenticateRegistrationForm(RegistrationForm registrationForm) {
		String email = registrationForm.getEmail();
		String password = registrationForm.getPassword();
		String confirmPassword = registrationForm.getConfirmPassword();
		int bankAccountNo = registrationForm.getBankAccountNo();

		if (password != null || confirmPassword != null) {
			if (!password.equals(confirmPassword)) {
				return true;
			}
		}
		return false;
	}

	public boolean authenticateLogin(User User) {
		Optional<User> foundUserOptional = userRepo.findByEmail(User.getEmail());
		if (foundUserOptional.isPresent()) {
			User foundUser = foundUserOptional.get();
			System.out.println("Found user: " + foundUser);

//			if (foundUser.getPassword().equals(User.getPassword())) {
			if (bCryptPasswordEncoder.matches(User.getPassword(), foundUser.getPassword())) {
				System.out.println("Password matched! " + foundUser);
				return true;
			}
		}
		return false;
	}

}
