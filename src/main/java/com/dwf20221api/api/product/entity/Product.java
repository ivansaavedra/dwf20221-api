package com.dwf20221api.api.product.entity;

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
@Table(name = "product")
public class Product {

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
	
	@JsonProperty("description")
	@Column(name = "description")
	private String description;
	
	@JsonProperty("price")
	@Column(name = "price")
	@NotNull(message="price is required")
	@Min(value=0, message="status must be positive")
	private Double price;
	
	@JsonProperty("stock")
	@Column(name = "stock")
	@NotNull(message="stock is required")
	@Min(value=1, message="status must be greater than 0")
	private Integer stock;
	
	@JsonProperty("id_category")
	@Column(name = "id_category")
	@NotNull(message="id_category is required")
	private Integer id_category;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	public Product() {
		
	}

	public Product(Integer id_product,
			@NotNull(message = "gtin is required") @Pattern(regexp = "[0-9]*", message = "gtin can only cointain numbers") String gtin,
			@NotNull(message = "product is required") String product, String description,
			@NotNull(message = "price is required") @Min(value = 0, message = "status must be positive") Double price,
			@NotNull(message = "stock is required") @Min(value = 1, message = "status must be greater than 0") Integer stock,
			@NotNull(message = "id_category is required") Integer id_category,
			@Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer status) {
		super();
		this.id_product = id_product;
		this.gtin = gtin;
		this.product = product;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.id_category = id_category;
		this.status = status;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getId_category() {
		return id_category;
	}

	public void setId_category(Integer id_category) {
		this.id_category = id_category;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Product [id_product=" + id_product + ", gtin=" + gtin + ", product=" + product + ", description="
				+ description + ", price=" + price + ", stock=" + stock + ", id_category=" + id_category + ", status="
				+ status + "]";
	}
}
