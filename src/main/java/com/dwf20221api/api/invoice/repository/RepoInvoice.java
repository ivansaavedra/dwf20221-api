package com.dwf20221api.api.invoice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwf20221api.api.invoice.entity.Invoice;

@Repository
public interface RepoInvoice extends JpaRepository<Invoice, Integer>{

	@Query(value ="SELECT * FROM invoice WHERE rfc = :rfc AND status = 1", nativeQuery = true)
	List<Invoice> getInvoices(@Param("rfc") String rfc);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE invoice "
					+ "SET subtotal = :subtotal, "
						+ "taxes = :taxes, "
						+ "total = :total, "
						+ "status = 1 "
					+ "WHERE id_invoice = :id_invoice", nativeQuery = true)
	Integer updateInvoice(
			@Param("id_invoice") Integer id_invoice,
			@Param("subtotal") Double subtotal,
			@Param("taxes") Double taxes,
			@Param("total") Double total
		);
}
