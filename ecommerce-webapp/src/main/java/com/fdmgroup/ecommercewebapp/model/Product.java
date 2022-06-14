package com.fdmgroup.ecommercewebapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fdmgroup.ecommercewebapp.enumeration.ProductCategory;

@Component
@Entity
@Table(name="productTable")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Product_ID")
	private int productId;
	private String productName;
	private double price;
	private String creator;
	private String productCategory;
	private String genre;
	private String description;
	private int quantity;
	
	@ManyToMany(mappedBy= "product")
	private List<Cart> cartList;
	
	public Product() {
	}

	public Product(int productId, String productName, double price, String creator, String productCategory,
			String genre, String description, int quantity) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.creator = creator;
		this.productCategory = productCategory;
		this.genre = genre;
		this.description = description;
		this.quantity = quantity;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", creator="
				+ creator + ", productCategory=" + productCategory + ", genre=" + genre + ", description=" + description
				+ ", quantity=" + quantity + "]";
	}

	
	
}
