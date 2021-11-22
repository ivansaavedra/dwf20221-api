package com.dwf20221api.api.product.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class DtoProductList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_product")
	@Column(name = "id_product")
	private Integer id_product;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@NotNull(message="gtin is required")
	@Pattern(regexp="[0-9]*", message="gtin can only cointain numbers")
	private String gtin;
	
	@JsonProperty("product")
	@Column(name = "product")
	@NotNull(message="product is required")
	private String product;
	
	public DtoProductList() {
		
	}

	public DtoProductList(Integer id_product,
			@NotNull(message = "gtin is required") @Pattern(regexp = "[0-9]*", message = "gtin can only cointain numbers") String gtin,
			@NotNull(message = "product is required") String product) {
		super();
		this.id_product = id_product;
		this.gtin = gtin;
		this.product = product;
	}

	public Integer getId_product() {
		return id_product;
	}

	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "DtoProductList [id_product=" + id_product + ", gtin=" + gtin + ", product=" + product + "]";
	}
	
}
