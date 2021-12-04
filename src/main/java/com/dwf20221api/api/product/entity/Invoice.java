package com.dwf20221api.api.product.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_invoice")
	@Column(name = "id_invoice")
	private Integer id_invoice;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc is required")
	@Pattern(regexp="^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))((-)?([A-Z\\d]{3}))?$", message="rfc has an invalid format")
	private String rfc;
	
	@JsonProperty("subtotal")
	@Column(name = "subtotal")
	private Double subtotal;
	
	@JsonProperty("taxes")
	@Column(name = "taxes")
	private Double taxes;
	
	@JsonProperty("total")
	@Column(name = "total")
	private Double total;
	
	@Column(name = "created_at")
	@JsonProperty("created_at")
	private LocalDate created_at;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	public Invoice() {
		
	}

	public Invoice(Integer id_invoice,
			@NotNull(message = "rfc is required") @Pattern(regexp = "^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))((-)?([A-Z\\d]{3}))?$", message = "rfc has an invalid format") String rfc,
			Double subtotal, Double taxes, Double total, LocalDate created_at,
			@Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer status) {
		super();
		this.id_invoice = id_invoice;
		this.rfc = rfc;
		this.subtotal = subtotal;
		this.taxes = taxes;
		this.total = total;
		this.created_at = created_at;
		this.status = status;
	}

	public Integer getId_invoice() {
		return id_invoice;
	}

	public void setId_invoice(Integer id_invoice) {
		this.id_invoice = id_invoice;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDate getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Invoice [id_invoice=" + id_invoice + ", rfc=" + rfc + ", subtotal=" + subtotal + ", taxes=" + taxes
				+ ", total=" + total + ", created_at=" + created_at + ", status=" + status + "]";
	}
}
