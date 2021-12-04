package com.dwf20221api.api.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.invoice.entity.Item;

@Repository
public interface RepoItem extends JpaRepository<Item, Integer>{

	@Query(value ="SELECT * FROM item WHERE id_invoice = :id_invoice AND status = 1", nativeQuery = true)
	List<Item> getItems(@Param("id_invoice") Integer id_invoice);
}
