package com.fdmgroup.ecommercewebapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.ecommercewebapp.model.Cart;
import com.fdmgroup.ecommercewebapp.model.Product;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer>{
	
	Optional<Cart> findByUserId(int userId);
	
	@Query(value = "SELECT * FROM CART_PRODUCT c WHERE c.CART_ID=:CART_ID", nativeQuery = true)
	List<Cart> findUserList(@Param("CART_ID") int cartId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO CART_PRODUCT(PRODUCT_ID, CART_ID) values (:PRODUCT_ID, :CART_ID)", nativeQuery = true)
	void InsertProductIntoCart(@Param("PRODUCT_ID") int productId, @Param("CART_ID") int cartId);
	
//	@Query(value = "Select PRODUCT_ID ,COUNT(PRODUCT_ID) FROM CART_PRODUCT c WHERE c.CART_ID=:CART_ID GROUP BY c.PRODUCT_ID, c.CART_ID HAVING COUNT(PRODUCT_ID) >= 1", nativeQuery= true)
//	int findQuantity(@Param("CART_ID") int cartId);

	
	
//	@Query("SELECT p FROM Product p WHERE" + " PRODUCT_ID LIKE" + "( SELECT PRODUCT_ID FROM CART_PRODUCT b where " + "CART_ID like ('%', :CART_ID, '%'))")
//	List<Product> findProductsInCart(@Param("CART_ID") int cartId);

//	Optional<Product> findByProductId(Integer productId);
	
//	@Query("select b from Cart_Table b where " + "lower(b.title) like concat('%', :searchedValue, '%') or " + 
//			"lower(b.genre) like concat('%', :searchedValue, '%')")
//	List<Product> AddProductToCart(@Param("searchedValue") String searchedValue);
}
