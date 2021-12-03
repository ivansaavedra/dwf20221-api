package com.dwf20221api.api.invoice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class ProductCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_product")
	@Column(name = "id_product")
	private Integer id_product;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@Pattern(regexp="[0-9]*", message="gtin can only cointain numbers")
	private String gtin;
	
	@JsonProperty("product")
	@Column(name = "product")
	private String product;

	@JsonProperty("price")
	@Column(name = "price")
	private Double price;
	
	@JsonProperty("stock")
	@Column(name = "stock")
	private Integer stock;
	
	public ProductCart() {
		
	}

	public ProductCart(Integer id_product,
			@NotNull(message = "gtin is required") @Pattern(regexp = "[0-9]*", message = "gtin can only cointain numbers") String gtin,
			@NotNull(message = "product is required") String product,
			@NotNull(message = "price is required") @Min(value = 0, message = "status must be positive") Double price,
			@NotNull(message = "stock is required") @Min(value = 1, message = "status must be greater than 0") Integer stock) {
		super();
		this.id_product = id_product;
		this.gtin = gtin;
		this.product = product;
		this.price = price;
		this.stock = stock;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductCart [id_product=" + id_product + ", gtin=" + gtin + ", product=" + product + ", price=" + price
				+ ", stock=" + stock + "]";
	}
}
