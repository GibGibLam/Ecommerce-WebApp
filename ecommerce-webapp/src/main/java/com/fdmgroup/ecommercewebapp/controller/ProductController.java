package com.fdmgroup.ecommercewebapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ecommercewebapp.model.Product;
import com.fdmgroup.ecommercewebapp.model.User;
import com.fdmgroup.ecommercewebapp.repository.IProductRepository;
import com.fdmgroup.ecommercewebapp.repository.IUserRepository;

@Controller
public class ProductController {

	@Autowired
	IProductRepository productRepo;

//	Create
	@RequestMapping("addProduct")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";
	}

	@PostMapping("saveProduct")
	public String saveProduct(Product product) {
		productRepo.save(product);
		System.out.println("Saving Product");
		System.out.println(product);
		return "redirect:index";
	}

	// Display

	@RequestMapping("displayProduct")
	public String displayProduct(Model model) {
		System.out.println("Displaying products...");
		List<Product> products = productRepo.findAll();
		products.forEach(System.out::println);
		model.addAttribute("products", products);
		return "individualcategory";
	}

	@RequestMapping({"", "index"})
	public String displayProduct2(Model model, HttpServletRequest request) {
		System.out.println("Displaying products2...");
		List<Product> products = productRepo.findAll();
		products.forEach(System.out::println);
		model.addAttribute("products", products);
		System.out.println("Current user is : " + request.getSession().getAttribute("email"));
		return "index";
	}

	// Update
	@RequestMapping("updateProduct")
	public String goToUpdateProduct(Model model) {
		model.addAttribute("product", new Product());
		return "updateProduct";
	}

	@RequestMapping("updatingProduct")
	public String updatingProduct(int productId, String productName, double price, String creator,
			String productCategory, String genre, String description, int quantity) {
		Product product = new Product();
		product.setProductId(productId);
		product.setProductName(productName);
		product.setPrice(price);
		product.setCreator(creator);
		product.setProductCategory(productCategory);
		product.setGenre(genre);
		product.setDescription(description);
		product.setQuantity(quantity);
		productRepo.save(product);
		System.out.println("Updating Product");
		System.out.println(product);
		return "redirect:index";
	}

	//Delete
	@RequestMapping("deleteProduct")
	public String goToDeleteProduct(Model model) {
		model.addAttribute("product", new Product());
		return "deleteProduct";
	}

	@RequestMapping("removeProduct")
	public String removeProduct(Integer productId) {
		productRepo.deleteById(productId);
		System.out.println("Deleting Product");
		return "redirect:index";
	}

	@RequestMapping("searchProduct")
	public String goToSearchProduct(@RequestParam(required = false) String searchedValue, Model model) {
		System.out.println("Searching product");
		System.out.println(searchedValue);
		if (searchedValue != null) {
			List<Product> searchedProducts = productRepo.findByKeyword(searchedValue);
			model.addAttribute("searchedProducts", searchedProducts);
		}
		return "searchProduct";
	}
}
