package com.dwf20221api.api.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.product.dto.DtoProductCategory;

@Repository
public interface RepoProductCategory extends JpaRepository<DtoProductCategory, Integer>{
	
	@Query(value ="SELECT * FROM product WHERE id_category = :id_category AND status = 1", nativeQuery = true)
	List<DtoProductCategory> findByCategory(@Param("id_category") Integer id_category);
	
	@Query(value ="SELECT * FROM product WHERE status = 1 ORDER BY RAND() LIMIT 6;", nativeQuery = true)
	List<DtoProductCategory> findByRandom();

}
