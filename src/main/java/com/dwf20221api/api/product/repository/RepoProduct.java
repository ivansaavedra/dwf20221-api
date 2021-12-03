package com.dwf20221api.api.product.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.product.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer>{
	
	@Query(value ="SELECT id_product FROM product WHERE id_category = :id_category", nativeQuery = true)
	List<Integer> findByCategory(@Param("id_category") Integer id_category);
	
	@Query(value ="SELECT * FROM product WHERE gtin = :gtin", nativeQuery = true)
	Product findByGtin(@Param("gtin") String gtin);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE product SET id_category = :id_category WHERE id_product = :id_product", nativeQuery = true)
	Integer updateProductCategory(@Param("id_category") Integer id_category, @Param("id_product") Integer id_product);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE product "
					+ "SET gtin = :gtin, "
						+ "product = :product, "
						+ "description = :description, "
						+ "price = :price, "
						+ "stock = :stock "
					+ "WHERE id_product = :id_product", nativeQuery = true)
	Integer updateProduct(
			@Param("id_product") Integer id_product,
			@Param("gtin") String gtin, 
			@Param("product") String product, 
			@Param("description") String description, 
			@Param("price") Double price, 
			@Param("stock") Integer stock
		);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE product SET status = 0 WHERE id_product = :id_product", nativeQuery = true)
	Integer deleteProduct(@Param("id_product") Integer id_product);
	
	@Query(value ="SELECT stock FROM product WHERE id_product = :id_product", nativeQuery = true)
	Integer getStock(@Param("id_product") Integer id_product);
}
