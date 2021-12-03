package com.dwf20221api.api.product.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Where;

import com.dwf20221api.api.product.entity.ProductImage;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class DtoProductCategory {

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
	
	@JsonProperty("price")
	@Column(name = "price")
	@NotNull(message="price is required")
	@Min(value=0, message="status must be positive")
	private Double price;
	
	@JoinColumn(name = "id_product")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Where(clause = "status = 1")
	private List<ProductImage> images;
	
	public DtoProductCategory() {
		
	}

	public DtoProductCategory(Integer id_product,
			@NotNull(message = "gtin is required") @Pattern(regexp = "[0-9]*", message = "gtin can only cointain numbers") String gtin,
			@NotNull(message = "product is required") String product,
			@NotNull(message = "price is required") @Min(value = 0, message = "status must be positive") Double price,
			List<ProductImage> images) {
		super();
		this.id_product = id_product;
		this.gtin = gtin;
		this.product = product;
		this.price = price;
		this.images = images;
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

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "DtoProductCategory [id_product=" + id_product + ", gtin=" + gtin + ", product=" + product + ", price="
				+ price + ", images=" + images + "]";
	}
}
