package com.fdmgroup.ecommercewebapp.model;

import java.util.List;

import javax.persistence.CascadeType;

//import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name = "cartTable")

public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;

	private double priceTotal;
	private int quantity;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "Cart_Product", joinColumns = @JoinColumn(name="CART_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	private List<Product> product;
	private int userId;
	
	public Cart() {
	}
	
	
	public Cart(int cartId, double priceTotal, int quantity, List<Product> product, int userId) {
		this.cartId = cartId;
		this.priceTotal = priceTotal;
		this.quantity = quantity;
		this.product = product;
		this.userId = userId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public double getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", priceTotal=" + priceTotal + ", quantity=" + quantity + ", userId=" + userId
				+ "]";
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}

	





}
