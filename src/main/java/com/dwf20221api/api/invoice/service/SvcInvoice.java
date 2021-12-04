package com.dwf20221api.api.invoice.service;

import java.util.List;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.invoice.entity.Invoice;
import com.dwf20221api.api.invoice.entity.Item;

public interface SvcInvoice {

	public List<Invoice> getInvoices(String rfc);
	public List<Item> getItems(Integer id_invoice);
	public ApiResponse purchase(String rfc);
}
