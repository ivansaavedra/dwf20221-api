package com.dwf20221api.api.invoice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.dwf20221api.api.invoice.dto.ProductCart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_cart")
	@Column(name = "id_cart")
	private Integer id_cart;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc is required")
	@Pattern(regexp="^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))((-)?([A-Z\\d]{3}))?$", message="rfc has an invalid format")
	private String rfc;
	
	@JsonProperty("id_product")
	@Column(name = "id_product")
	@NotNull(message="id_product is required")
	private Integer id_product;
	
	@JsonProperty("quantity")
	@Column(name = "quantity")
	@NotNull(message="quantity is required")
	private Integer quantity;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", insertable = false, updatable = false)
	private ProductCart product;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	public Cart() {
		
	}

	public Cart(Integer id_cart, String rfc, Integer id_product, Integer quantity, ProductCart product,
			@Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer status) {
		super();
		this.id_cart = id_cart;
		this.rfc = rfc;
		this.id_product = id_product;
		this.quantity = quantity;
		this.product = product;
		this.status = status;
	}

	public Integer getId_cart() {
		return id_cart;
	}

	public void setId_cart(Integer id_cart) {
		this.id_cart = id_cart;
	}

	public Integer getId_product() {
		return id_product;
	}

	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductCart getProduct() {
		return product;
	}

	public void setProduct(ProductCart product) {
		this.product = product;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cart [id_cart=" + id_cart + ", rfc=" + rfc + ", id_product=" + id_product
				+ ", quantity=" + quantity + ", product=" + product + ", status=" + status + "]";
	}
}