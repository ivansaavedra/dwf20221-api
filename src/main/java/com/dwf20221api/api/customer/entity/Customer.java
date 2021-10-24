package com.dwf20221api.api.customer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_customer")
	@Column(name = "id_customer")
	private Integer id_customer;
	
	@JsonProperty("name")
	@Column(name = "name")
	@NotNull(message="name is required")
	@Pattern(regexp="[a-zA-Z0-9\s]*", message="name can only cointain letters")
	private String name;
	
	@JsonProperty("surname")
	@Column(name = "surname")
	@NotNull(message="surname is required")
	@Pattern(regexp="[a-zA-Z0-9\s]*", message="surname can only cointain letters")
	private String surname;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc is required")
	@Pattern(regexp="^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))((-)?([A-Z\\d]{3}))?$", message="rfc has an invalid format")
	private String rfc;
	
	@JsonProperty("mail")
	@Column(name = "mail")
	@NotNull(message="mail is required")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="mail has an invalid format")
	private String mail;
	
	@JsonProperty("address")
	@Column(name = "address")
	@NotNull(message="address is required")
	private String address;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	@JsonProperty("region")
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_region", referencedColumnName = "id_region")
	private Region region;
	
	@JsonProperty("image")
	@Transient
	private String image;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer_image", referencedColumnName = "id_customer_image")
	@JsonIgnore
	private CustomerImage customerImage;
}
