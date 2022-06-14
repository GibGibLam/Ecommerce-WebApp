package com.fdmgroup.ecommercewebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.ecommercewebapp.model.Order;
import com.fdmgroup.ecommercewebapp.repository.IOrderRepository;

@Controller
public class OrderController {

	@Autowired
	IOrderRepository orderRepo;
	
	@RequestMapping("addOrder")
	public String addOrder(Model model) {
	model.addAttribute("order", new Order());
	return "index";
	}
	
	@PostMapping("saveOrder")
	public String saveOrder(Order order) {
		orderRepo.save(order);
		System.out.println("Saving Order");
		System.out.println(order);
		return "index";
	}
}
