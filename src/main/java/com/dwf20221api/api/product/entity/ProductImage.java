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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product_image")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product_image")
	private Integer id_product_image;
	
	@JsonProperty("id_product")
	@Column(name = "id_product")
	@NotNull(message="id_product is required")
	private Integer id_product;
	
	@JsonProperty("image")
	@Column(name = "image")
	@NotNull(message="image is required")
	private String image;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	public ProductImage() {
		
	}

	public ProductImage(Integer id_product_image, @NotNull(message = "id_product is required") Integer id_product,
			@NotNull(message = "image is required") String image,
			@Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer status) {
		super();
		this.id_product_image = id_product_image;
		this.id_product = id_product;
		this.image = image;
		this.status = status;
	}

	public Integer getId_product_image() {
		return id_product_image;
	}

	public void setId_product_image(Integer id_product_image) {
		this.id_product_image = id_product_image;
	}

	public Integer getId_product() {
		return id_product;
	}

	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductImage [id_product_image=" + id_product_image + ", id_product=" + id_product + ", image=" + image
				+ ", status=" + status + "]";
	}
}
