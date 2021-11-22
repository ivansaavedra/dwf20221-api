package com.dwf20221api.api.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.product.dto.DtoProductList;

@Repository
public interface RepoProductList extends JpaRepository<DtoProductList, Integer>{
	
	@Query(value ="SELECT id_product, gtin, product FROM product WHERE status = 1", nativeQuery = true)
	List<DtoProductList> findByStatus();

}
