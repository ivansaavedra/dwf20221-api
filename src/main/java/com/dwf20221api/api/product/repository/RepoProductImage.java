package com.dwf20221api.api.product.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.product.entity.ProductImage;

@Repository
public interface RepoProductImage  extends JpaRepository<ProductImage, Integer>{
	
	@Query(value ="SELECT * FROM product_image WHERE id_product = :id_product AND status = 1", nativeQuery = true)
	List<ProductImage> findByStatus(@Param("id_product") Integer id_product);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE product_image SET status = 0 WHERE id_product_image = :id_product_image", nativeQuery = true)
	Integer deleteProductImage(@Param("id_product_image") Integer id_product_image);
	
	@Query(value ="SELECT count(*) FROM product_image WHERE id_product = :id_product AND status = 1", nativeQuery = true)
	Integer countImages(@Param("id_product") Integer id_product);

}
