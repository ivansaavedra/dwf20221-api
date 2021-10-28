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
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_category")
	@Column(name = "id_category")
	private Integer id_category;
	
	@JsonProperty("category")
	@Column(name = "category")
	@NotNull(message="category is required")
	private String category;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	public Category() {
		
	}

	public Category(Integer id_category, @NotNull(message = "category is required") String category,
			@Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer status) {
		super();
		this.id_category = id_category;
		this.category = category;
		this.status = status;
	}

	public Integer getId_category() {
		return id_category;
	}

	public void setId_category(Integer id_category) {
		this.id_category = id_category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Category [id_category=" + id_category + ", category=" + category + ", status=" + status + "]";
	}
}
