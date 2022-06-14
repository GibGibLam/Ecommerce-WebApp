package com.fdmgroup.ecommercewebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ecommercewebapp.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

	@Query("select b from Product b where " + "lower(b.productName) like concat('%', :searchedValue, '%') or " + "lower(b.productCategory) like concat('%', :searchedValue, '%') or " + 
			"lower(b.creator) like concat('%', :searchedValue, '%') or " + "lower(b.genre) like concat('%', :searchedValue, '%')")
	List<Product> findByKeyword(@Param("searchedValue") String searchedValue);
}
