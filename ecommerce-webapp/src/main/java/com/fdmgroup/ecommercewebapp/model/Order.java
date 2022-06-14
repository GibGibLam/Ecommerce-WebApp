package com.fdmgroup.ecommercewebapp.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fdmgroup.ecommercewebapp.enumeration.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Table(name="orderTable")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int orderId;
	private Date dateOrder;
	private Date dateComplete;
	private OrderStatus orderStatus;
	//productId, quantity
//	private Map<Integer, Integer> productMap;
	private double priceTotal;
	
}
