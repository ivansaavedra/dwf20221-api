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
@Table(name = "region")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_region")
	@Column(name = "id_region")
	private Integer id_region;
	
	@JsonProperty("region")
	@Column(name = "region")
	@NotNull(message="region is required")
	private String region;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	public Region() {
		
	}

	public Region(Integer id_region, @NotNull(message = "region is required") String region,
			@Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer status) {
		super();
		this.id_region = id_region;
		this.region = region;
		this.status = status;
	}

	public Integer getId_region() {
		return id_region;
	}

	public void setId_region(Integer id_region) {
		this.id_region = id_region;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Region [id_region=" + id_region + ", region=" + region + ", status=" + status + "]";
	}
}