package com.fdmgroup.ecommercewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ecommercewebapp.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>{

}
