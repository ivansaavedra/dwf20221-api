package com.dwf20221api.api.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.invoice.entity.Invoice;
import com.dwf20221api.api.invoice.entity.Item;
import com.dwf20221api.api.invoice.service.SvcInvoice;

@RestController
@RequestMapping("/invoice")
public class CtrlInvoice {

	@Autowired
	SvcInvoice svcInvoice;
	
	@GetMapping("/{rfc}")
	public ResponseEntity<List<Invoice>> getCart(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcInvoice.getInvoices(rfc), HttpStatus.OK);
	}
	
	@GetMapping("/item/{id_invoice}")
	public ResponseEntity<List<Item>> getItems(@PathVariable("id_invoice") Integer id_invoice){
		return new ResponseEntity<>(svcInvoice.getItems(id_invoice), HttpStatus.OK);
	}
	
	@PostMapping("/{rfc}")
	public ResponseEntity<ApiResponse> purchase(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcInvoice.purchase(rfc),HttpStatus.CREATED);
	}
}
