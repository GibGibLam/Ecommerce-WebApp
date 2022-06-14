package com.fdmgroup.ecommercewebapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.ecommercewebapp.model.Product;
import com.fdmgroup.ecommercewebapp.model.RegistrationForm;
import com.fdmgroup.ecommercewebapp.model.User;
import com.fdmgroup.ecommercewebapp.repository.IUserRepository;
import com.fdmgroup.ecommercewebapp.service.AuthenticationService;
import com.fdmgroup.ecommercewebapp.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	AuthenticationService authenticationService;
	
	//Create
	@RequestMapping("register")
	public String registerUser(Model model, HttpServletRequest request) {
		System.out.println("Goin to registeration...");
		System.out.println((String) request.getSession().getAttribute("email"));
		model.addAttribute("registrationForm", new RegistrationForm());
		model.addAttribute("errorMessage", "");
		return "register";
	}
	
	@PostMapping("saveUser")
	public String saveUser(User user) {
		userRepo.save(user);
		System.out.println("Saving User...");
		System.out.println(user);
		return "redirect:index";
	}
	
	//Read
	
//	@RequestMapping("displayUser")
//	public void displayUser(HttpServletRequest request,Model model) {
//		System.out.println("Displaying current User...");
//		String emailTemp = (String) request.getSession().getAttribute("email");
//		Optional<User> userOptional = userRepo.findByEmail(emailTemp);
//		System.out.println(userOptional);
//		User foundUser = null;
//		if (userOptional.isPresent()) {
//			foundUser = userOptional.get();
//	
//		}
//		model.addAttribute("user2", foundUser);
//	}
//	
	//Update
	@RequestMapping("updateUser")
	public String goToUpdateUser(Model model) {
		model.addAttribute("user", new User());
		return "updateUser";
	}
	@RequestMapping("updatingUser")
	public String updatingUser(int userId, String email, String password, int bankAccountNo ) {
	User user = new User();
		user.setUserId(userId);
		user.setEmail(email);
		user.setPassword(password);
		user.setBankAccountNo(bankAccountNo);
		userRepo.save(user);
		System.out.println("Updating user");
		System.out.println(user);
		return "redirect:index";
	}
	
	//Delete	
	@RequestMapping("deleteUser")
	public String deleteUser(Model model) {
		model.addAttribute("user", new User());
		return "deleteUser";
	}
	
	@RequestMapping("removeUser")
	public String removeUser(Integer userId) {
		userRepo.deleteById(userId);
		System.out.println("Deleting User");
		return "redirect:index";
		
	}
	
	@RequestMapping("login")
	public String goToLoginPage(Model model) {
		System.out.println("Display login page");
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/authenticateRegistration")
	public String authenticateRegistration(Model model, RegistrationForm registrationForm) {
		System.out.println("Authenticating registration...");
		model.addAttribute("errorMessage", null);

		if (authenticationService.authenticateRegistrationForm(registrationForm)) {
			model.addAttribute("errorMessage", "Password doesn't match!");
		} else {
			userService.saveRegisteredUser(registrationForm);
			return "redirect:login";
		}

		return "register";
	}


	
//	@PostMapping("authenticateLogin")
//	public String authenticateLogin(HttpServletRequest request,User user, Model model) {
//		System.out.println("Authenticating User...");
//		System.out.println(user);
//		Optional<User> userOptional = userRepo.findByEmail(user.getEmail());
//		if(userOptional.isPresent()) {
//			if(userOptional.get().getPassword().equals(user.getPassword())) {
//				request.getSession().setAttribute("email", user.getEmail());
//				return "redirect:index";
//			}	
//				
//		return "login";
//		}
//		
//		model.addAttribute("errorMessage", "Invalid username or password.");
//		return "login";
//	}

	@PostMapping("/authenticateLogin")
	public String authenticateLogin(HttpServletRequest request, Model model, User User) {
		if (authenticationService.authenticateLogin(User)) {
			System.out.println("Authenticated Login user...");
			request.getSession().setAttribute("email", User.getEmail());
			System.out.println("User: "+ (String) request.getSession().getAttribute("email") + " is logged in successfully!");
			return "redirect:main";
		}
		model.addAttribute("errorMessage", "Invalid email or password.");
		return "login";
	}
	
	@RequestMapping("main")
	public String goToMain(HttpServletRequest request) {
		String sessionEmail = (String) request.getSession().getAttribute("email");
		return authenticationService.authenticateUserSession(sessionEmail) ? "main" : "error";
	}
//

	@RequestMapping("/logout")
	public String goToRegistration(HttpServletRequest request) {
		System.out.println((String) request.getSession().getAttribute("email") + " is logging out");
		System.out.println("Logging out...");
		request.getSession().invalidate();
		System.out.println("Checking for user after logout...");
		System.out.println("User is now :" + (String) request.getSession().getAttribute("email"));
		return "redirect:index";
	}

}
