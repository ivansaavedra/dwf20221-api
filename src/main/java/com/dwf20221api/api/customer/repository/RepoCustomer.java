package com.dwf20221api.api.customer.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.customer.entity.Customer;

@Repository
public interface RepoCustomer extends JpaRepository<Customer, Integer>{

	@Modifying
	@Transactional
	@Query(value ="UPDATE customer_image I "
		    + "INNER JOIN customer C ON (I.id_customer_image = C.id_customer_image) "
			+ "SET I.status = 0, C.status=0 "
			+ "WHERE C.id_customer = :id_customer", nativeQuery = true)
	Integer deleteByIdCustomer(@Param("id_customer") Integer id_customer);
	
	@Query(value ="SELECT id_customer FROM customer WHERE id_region = :id_region", nativeQuery = true)
	List<Integer> findByRegion(@Param("id_region") Integer id_region);
	
	@Query(value ="SELECT * FROM customer WHERE rfc = :rfc", nativeQuery = true)
	Customer findByRfc(@Param("rfc") String rfc);
	
	@Query(value ="SELECT I.id_customer_image FROM customer C INNER JOIN customer_image I ON (c.id_customer_image = I.id_customer_image) WHERE id_customer = :id_customer", nativeQuery = true)
	Integer findIdCustomerImage(@Param("id_customer") Integer id_customer);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE customer_image I "
				    + "INNER JOIN customer C ON (I.id_customer_image = C.id_customer_image) "
					+ "SET image = :image "
					+ "WHERE C.id_customer = :id_customer", nativeQuery = true)
	Integer updateCustomerImage(@Param("image") String image, @Param("id_customer") Integer id_customer);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE customer SET id_region = :id_region WHERE id_customer = :id_customer", nativeQuery = true)
	Integer updateCustomerRegion(@Param("id_region") Integer id_region, @Param("id_customer") Integer id_customer);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE customer "
					+ "SET name = :name, "
						+ "surname = :surname, "
						+ "rfc = :rfc, "
						+ "mail = :mail, "
						+ "address = :address "
					+ "WHERE id_customer = :id_customer", nativeQuery = true)
	Integer updateCustomer(
			@Param("id_customer") Integer id_customer,
			@Param("name") String name, 
			@Param("surname") String surname, 
			@Param("rfc") String rfc, 
			@Param("mail") String mail, 
			@Param("address") String address
		);

}
