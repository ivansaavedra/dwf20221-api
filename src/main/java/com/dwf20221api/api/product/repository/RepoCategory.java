package com.dwf20221api.api.product.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.product.entity.Category;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{

	@Query(value ="SELECT * FROM category WHERE status = :status", nativeQuery = true)
	List<Category> findByStatus(@Param("status") Integer status);
	
	@Query(value ="SELECT * FROM category WHERE category = :category AND status = 0", nativeQuery = true)
	Category getCategoryByName(@Param("category") String category);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE category SET status = 1 WHERE category = :category", nativeQuery = true)
	void updateExistingCategory(@Param("category") String category);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE category SET status = 0 WHERE id_category = :id_category", nativeQuery = true)
	void deleteById(@Param("id_category") Integer id_category);

}
