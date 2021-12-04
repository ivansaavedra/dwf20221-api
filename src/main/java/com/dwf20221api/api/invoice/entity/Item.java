package com.dwf20221api.api.invoice.entity;

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
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id_item")
	@Column(name = "id_item")
	private Integer id_item;
	
	@JsonProperty("id_invoice")
	@Column(name = "id_invoice")
	@NotNull(message="id_invoice is required")
	private Integer id_invoice;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@NotNull(message="gtin code is required")
	@Pattern(regexp="[0-9]*", message="gtin can only cointain numbers")
	private String gtin;
	
	@JsonProperty("quantity")
	@Column(name = "quantity")
	@NotNull(message="quantity is required")
	@Min(value=1, message="quantity must be greater than 0")
	private Integer quantity;
	
	@JsonProperty("unit_price")
	@Column(name = "unit_price")
	private Double unit_price;
	
	@JsonProperty("taxes")
	@Column(name = "taxes")
	private Double taxes;
	
	@JsonProperty("subtotal")
	@Column(name = "subtotal")
	private Double subtotal;
	
	@JsonProperty("total")
	@Column(name = "total")
	private Double total;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;

	public Item() {
		
	}

	public Item(Integer id_item, @NotNull(message = "id_invoice is required") Integer id_invoice,
			@NotNull(message = "gtin code is required") @Pattern(regexp = "[0-9]*", message = "gtin can only cointain numbers") String gtin,
			@NotNull(message = "quantity is required") @Min(value = 1, message = "quantity must be greater than 0") Integer quantity,
			Double unit_price, Double taxes, Double subtotal, Double total,
			@Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer status) {
		super();
		this.id_item = id_item;
		this.id_invoice = id_invoice;
		this.gtin = gtin;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.taxes = taxes;
		this.subtotal = subtotal;
		this.total = total;
		this.status = status;
	}



	public Integer getId_item() {
		return id_item;
	}

	public void setId_item(Integer id_item) {
		this.id_item = id_item;
	}

	public Integer getId_invoice() {
		return id_invoice;
	}

	public void setId_invoice(Integer id_invoice) {
		this.id_invoice = id_invoice;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Item [id_item=" + id_item + ", id_invoice=" + id_invoice + ", gtin=" + gtin + ", quantity=" + quantity
				+ ", unit_price=" + unit_price + ", taxes=" + taxes + ", subtotal=" + subtotal
				+ ", total=" + total + ", status=" + status + "]";
	}
	
}
