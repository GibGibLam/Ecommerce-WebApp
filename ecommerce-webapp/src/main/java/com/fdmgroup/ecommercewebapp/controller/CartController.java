package com.fdmgroup.ecommercewebapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ecommercewebapp.model.Cart;
import com.fdmgroup.ecommercewebapp.model.Product;
import com.fdmgroup.ecommercewebapp.model.User;
import com.fdmgroup.ecommercewebapp.repository.ICartRepository;
import com.fdmgroup.ecommercewebapp.repository.IUserRepository;

@Controller
public class CartController {

	private Map<Integer, Integer> quantityMap = new HashMap<>();

	@Autowired
	Cart cart;

	@Autowired
	ICartRepository cartRepo;

	@Autowired
	IUserRepository userRepo;

	@RequestMapping("createCart")
	public String createCart(Model model) {
		model.addAttribute("cart", new Cart());
		return "redirect:index";
	}

//	@PostMapping("saveCart")
//	public String saveProductToCart(Product product) {
//		cartRepo.save(product.getProductId());
//		System.out.println("Saving Product to Cart");
//		System.out.println(product);
//		return "index";
//	}
	@RequestMapping("addProductToCart")
	public String addProductToCart(@RequestParam(required = false) Integer productId, Product product, Model model,
			HttpServletRequest request) {
		System.out.println("Adding product to cart");
		System.out.println(productId);
		System.out.println(product.getProductId());
		System.out.println(request.getSession().getAttribute("email"));
		String email = (String) request.getSession().getAttribute("email");

		Optional<User> userOptional = userRepo.findByEmail(email);
		int cartId = 0;
		if (userOptional.isPresent()) {
			System.out.println("User is present");
			int userId = userOptional.get().getUserId();

			Optional<Cart> cartOptional = cartRepo.findByUserId(userId);

			if (cartOptional.isPresent()) {
				System.out.println("Cart is present");
				cartId = cartOptional.get().getCartId();
				System.out.println("cart id: " + cartId);
			} else {
				System.out.println("Creating a new cart...");
				cart.setUserId(userId);
				cartRepo.save(cart);
			}

			// Cart is saved and products is added here
			cartRepo.InsertProductIntoCart(productId, cartId);
			createQuantityMap(request);
		}
		return "redirect:index";

	}

//			Optional<Product> productOptional = cartRepo.findByProductId(productId);
//			if(productOptional.isPresent()) {
//				System.out.println("Product exist in cart already");
//				cart.setQuantity(productId) = cart.getQuantity + 1;
//			}
//			else {
	// to eliminate duplicate products
//			List<Cart> cartProducts = cartRepo.findUserList(cartId);
//			if (cartProducts.isEmpty()) {
//				System.out.println("Cart is empty, adding product" + productId + " for first time");
//				cartRepo.InsertProductIntoCart(productId, cartId);
//			} else {
//				cartProducts.forEach(System.out::println);
//				int productId2 = 0;
//				List<Product> productList = new ArrayList<>();
//				for (Cart cart : cartProducts) {
//					productList = cart.getProduct();
//					for (Product item : productList) {
//						productId2 = item.getProductId();
//						if (productId2 == productId) {
//							System.out.println("Product already exist in the cart");
//							return "redirect:shoppingcart";
//						} else {
//							System.out.println("Adding Product to cart");
//							cartRepo.InsertProductIntoCart(productId, cartId);
//						}
//					}
//				}
//			}
//		}
//		if (productId != null) {
//			List<Product> cartProducts = cartRepo.addProductToCart;
//			model.addAttribute("cartProducts", cartProducts);
//		}

	public void createQuantityMap(HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");

		Optional<User> userOptional = userRepo.findByEmail(email);
		int cartId = 0;
		if (userOptional.isPresent()) {
			System.out.println("User is present");
			int userId = userOptional.get().getUserId();

			Optional<Cart> cartOptional = cartRepo.findByUserId(userId);

			if (cartOptional.isPresent()) {
				System.out.println("Cart is present");
				cartId = cartOptional.get().getCartId();
				System.out.println("cart id: " + cartId);
			}
			List<Cart> cartProducts = cartRepo.findUserList(cartId);
			cartProducts.forEach(System.out::println);
			int productId2;
			List<Product> productList = new ArrayList<>();
			// productid, quantity
			for (Cart cart : cartProducts) {
				productList = cart.getProduct();
			}
			System.out.println("Printing productList...");
			productList.forEach(System.out::println);
			for (Product item : productList) {
				productId2 = item.getProductId();
				if (quantityMap.containsKey(productId2)) {
					System.out.println("Incrementing quantity...");
					int currentQuantity = quantityMap.get(productId2);
					quantityMap.put(productId2, ++currentQuantity);
				} else {
					System.out.println("Putting product in map");
					quantityMap.put(productId2, 1);
				}
			}
			System.out.println("Map is printing");
			quantityMap.forEach((k, v) -> System.out.println(k + " " + v + " "));
		}
	}

	@RequestMapping("checkoutPage")
	public String goToCheckoutPage(HttpServletRequest request, Model model) {
		System.out.println("going to checkout page");
		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		System.out.println(email);
		Optional<User> userOptional = userRepo.findByEmail(email);
		if (userOptional.isPresent()) {
			System.out.println("User is present");
			int userId = userOptional.get().getUserId();
			Cart cartFromRepo = cartRepo.findByUserId(userId).get();
			int cartIdFromDb = cartRepo.findByUserId(userId).get().getCartId();
			System.out.println("cartIdFromDb " + cartIdFromDb);
			List<Cart> cartProducts = cartRepo.findUserList(cartIdFromDb);

			cartProducts.forEach(System.out::println);

			List<Product> productList = new ArrayList<>();
			for (Cart cart : cartProducts) {
				productList = cart.getProduct();
			}
			quantityMap.clear();
			createQuantityMap(request);
			List<Product> tempProductList = productList;
			productList.forEach(System.out::println);
			for (int i = 0; i < productList.size(); i++) {
				for (Map.Entry<Integer, Integer> quantityEntry : quantityMap.entrySet()) {
					if (productList.get(i).getProductId() == quantityEntry.getKey()) {
						tempProductList.get(i).setQuantity(quantityEntry.getValue());
					}

				}
			}
			Set<Product> productSet = new HashSet<>(tempProductList);
			tempProductList.clear();
			tempProductList.addAll(productSet);
			double priceTotal =0;
			for (int j = 0; j < tempProductList.size(); j++) {
				for (Map.Entry<Integer, Integer> quantityEntry : quantityMap.entrySet()) {
					if (tempProductList.get(j).getProductId() == quantityEntry.getKey()) {
						int productQuantity = 0;
						productQuantity = quantityEntry.getValue();
						System.out.println("quantity of product is: " + productQuantity);
						Product product = tempProductList.get(j);
						double subtotal = product.getPrice() * productQuantity;
						System.out.println("subtotal price is : " + subtotal);
						priceTotal += subtotal;	
						cartFromRepo.setPriceTotal(priceTotal);
					}
				}
			}
			System.out.println("priceTotal of CartID: " + cartIdFromDb + " is " + priceTotal);
			model.addAttribute("cart", cartFromRepo);
		}
		return "checkout";
	}
	@RequestMapping("shoppingcart")
	public String displayCart(HttpServletRequest request, Model model) {
		System.out.println("Displaying Cart");
		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		Optional<User> userOptional = userRepo.findByEmail(email);
		if (userOptional.isPresent()) {
			System.out.println("User is present");
			int userId = userOptional.get().getUserId();
			Cart cartFromRepo = cartRepo.findByUserId(userId).get();
			int cartIdFromDb = cartRepo.findByUserId(userId).get().getCartId();
			System.out.println("cartIdFromDb " + cartIdFromDb);
			List<Cart> cartProducts = cartRepo.findUserList(cartIdFromDb);

			cartProducts.forEach(System.out::println);

			List<Product> productList = new ArrayList<>();
			for (Cart cart : cartProducts) {
				productList = cart.getProduct();
			}
			quantityMap.clear();
			createQuantityMap(request);
			List<Product> tempProductList = productList;
			productList.forEach(System.out::println);
			for (int i = 0; i < productList.size(); i++) {
				for (Map.Entry<Integer, Integer> quantityEntry : quantityMap.entrySet()) {
					if (productList.get(i).getProductId() == quantityEntry.getKey()) {
						tempProductList.get(i).setQuantity(quantityEntry.getValue());


					}

				}
			}
			//To remove duplicate products
			Set<Product> productSet = new HashSet<>(tempProductList);
			tempProductList.clear();
			tempProductList.addAll(productSet);
			
			double priceTotal =0;
			for (int j = 0; j < tempProductList.size(); j++) {
				for (Map.Entry<Integer, Integer> quantityEntry : quantityMap.entrySet()) {
					if (tempProductList.get(j).getProductId() == quantityEntry.getKey()) {
						int productQuantity = 0;
						productQuantity = quantityEntry.getValue();
						System.out.println("quantity of product is: " + productQuantity);
						Product product = tempProductList.get(j);
						double subtotal = product.getPrice() * productQuantity;
						System.out.println("subtotal price is : " + subtotal);
						priceTotal += subtotal;	
						cartFromRepo.setPriceTotal(priceTotal);
					}
				}
			}
			System.out.println("priceTotal of CartID: " + cartIdFromDb + " is " + priceTotal);
			model.addAttribute("cart", cartFromRepo);
			
			System.out.println("Map is printing second");
			quantityMap.forEach((k, v) -> System.out.println(k + " " + v + " "));
			System.out.println("Printing tempProductList...");
			tempProductList.forEach(System.out::println);
			model.addAttribute("productList", tempProductList);
		}
		return "shoppingcart";
	}

	@RequestMapping("checkout")
	public String checkout(HttpServletRequest request, Model model) {
		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		Optional<User> userOptional = userRepo.findByEmail(email);
		if (userOptional.isPresent()) {
			System.out.println("User is present");
			int userId = userOptional.get().getUserId();

			Optional<Cart> cartOptional = cartRepo.findByUserId(userId);
			if (cartOptional.isPresent()) {
				System.out.println("clearing cart");
				cartRepo.delete(cartOptional.get());
				cartRepo.delete(cart);
				System.out.println("Deleting cart...");
				cartRepo.save(cart);

				Cart cart = new Cart();
				System.out.println("Creating new cart...");
				cartRepo.save(cart);
				model.addAttribute("cart", cart);

				return "shoppingcart";
			}
		}
		return "shoppingcart";
	}
}
