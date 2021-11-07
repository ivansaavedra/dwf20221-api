package com.dwf20221api.api.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.customer.dto.DtoCustomerList;

@Repository
public interface RepoCustomerList extends JpaRepository<DtoCustomerList, Integer>{
	
	@Query(value ="SELECT id_customer, name, surname, rfc FROM customer WHERE status = 1", nativeQuery = true)
	List<DtoCustomerList> findByStatus();
}
