package com.fdmgroup.ecommercewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.ecommercewebapp.model.RegistrationForm;
import com.fdmgroup.ecommercewebapp.model.User;
import com.fdmgroup.ecommercewebapp.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	IUserRepository userRepo;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public void saveRegisteredUser(RegistrationForm registrationForm) {
		System.out.println("Registering...");
		System.out.println(bCryptPasswordEncoder.encode(registrationForm.getPassword()));
		userRepo.save(new User(registrationForm.getEmail(), bCryptPasswordEncoder.encode(registrationForm.getPassword()) , registrationForm.getBankAccountNo()));
	}

}
