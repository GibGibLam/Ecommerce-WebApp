package com.fdmgroup.ecommercewebapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.ecommercewebapp.model.User;

@Controller
public class WebController {
	
	@RequestMapping("product")
	public String goToProductPage() {
		return "product";
	}
	
	
	@RequestMapping("help")
	public String goToHelpPage() {
		return "help";
	}
	
	
	@RequestMapping("category")
	public String goToCategoryPage() {
		return "category";
	}
	
	
}
