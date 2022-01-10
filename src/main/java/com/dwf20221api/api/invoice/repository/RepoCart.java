package com.dwf20221api.api.invoice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.invoice.entity.Cart;

@Repository
public interface RepoCart extends JpaRepository<Cart, Integer>{

	@Query(value ="SELECT * FROM cart WHERE rfc = :rfc AND status = 1", nativeQuery = true)
	List<Cart> findByRfc(@Param("rfc") String rfc);
	
	@Query(value ="SELECT * FROM cart WHERE id_product = :id_product AND rfc=:rfc AND status = 1", nativeQuery = true)
	Cart findByProduct(@Param("id_product") Integer id_product, @Param("rfc") String rfc);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE cart SET quantity = :quantity WHERE id_cart = :id_cart", nativeQuery = true)
	Integer updateQuantity(@Param("id_cart") Integer id_cart, @Param("quantity") Integer quantity);

	@Modifying
	@Transactional
	@Query(value ="UPDATE cart SET status = 0 WHERE id_cart = :id_cart", nativeQuery = true)
	void removeFromCart(@Param("id_cart") Integer id_cart);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE cart SET status = 0 WHERE rfc = :rfc", nativeQuery = true)
	void deleteCart(@Param("rfc") String rfc);
}
