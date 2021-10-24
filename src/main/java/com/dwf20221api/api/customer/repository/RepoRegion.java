package com.dwf20221api.api.customer.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.customer.entity.Region;

@Repository
public interface RepoRegion extends JpaRepository<Region, Integer>{

	@Query(value ="SELECT * FROM region WHERE status = :status", nativeQuery = true)
	List<Region> findByStatus(@Param("status") Integer status);
	
	@Query(value ="SELECT * FROM region WHERE region = :region AND status = 0", nativeQuery = true)
	Region getRegionByName(@Param("region") String region);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE region SET status = 1 WHERE region = :region", nativeQuery = true)
	void updateExistingRegion(@Param("region") String region);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE region SET status = 0 WHERE id_region = :id_region", nativeQuery = true)
	void deleteById(@Param("id_region") Integer id_region);
	
}
