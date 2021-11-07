package com.dwf20221api.api.customer.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "customer")
public class DtoCustomerList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_customer")
	@Column(name = "id_customer")
	private Integer id_customer;
	
	@JsonProperty("name")
	@Column(name = "name")
	private String name;
	
	@JsonProperty("surname")
	@Column(name = "surname")
	private String surname;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	private String rfc;
	
	public DtoCustomerList() {
		
	}

	public DtoCustomerList(Integer id_customer, String name, String surname, String rfc) {
		super();
		this.id_customer = id_customer;
		this.name = name;
		this.surname = surname;
		this.rfc = rfc;
	}

	public Integer getId_customer() {
		return id_customer;
	}

	public void setId_customer(Integer id_customer) {
		this.id_customer = id_customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Override
	public String toString() {
		return "DtoCustomerList [id_customer=" + id_customer + ", name=" + name + ", surname=" + surname + ", rfc="
				+ rfc + "]";
	}
}
