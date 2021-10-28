package com.dwf20221api.api.customer.entity;

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
@Table(name = "customer_image")
public class CustomerImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_customer_image")
	private Integer id_customer_image;
	
	@JsonProperty("image")
	@Column(name = "image")
	@NotNull(message="image is required")
	private String image;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	public CustomerImage() {
		
	}

	public CustomerImage(Integer id_customer_image, @NotNull(message = "image is required") String image,
			@Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer status) {
		super();
		this.id_customer_image = id_customer_image;
		this.image = image;
		this.status = status;
	}

	public Integer getId_customer_image() {
		return id_customer_image;
	}

	public void setId_customer_image(Integer id_customer_image) {
		this.id_customer_image = id_customer_image;
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
		return "CustomerImage [id_customer_image=" + id_customer_image + ", image=" + image + ", status=" + status
				+ "]";
	}
}
