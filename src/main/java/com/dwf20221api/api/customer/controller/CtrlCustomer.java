package com.dwf20221api.api.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.customer.entity.Customer;
import com.dwf20221api.api.customer.entity.CustomerImage;
import com.dwf20221api.api.customer.entity.Region;
import com.dwf20221api.api.customer.service.SvcCustomer;
import com.dwf20221api.exceptionHandling.ApiException;

@RestController
@RequestMapping("/customer")
public class CtrlCustomer {
	
	@Autowired
	SvcCustomer svcCustomer;
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers(){
		return new ResponseEntity<>(svcCustomer.getCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/{rfc}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcCustomer.getCustomer(rfc), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcCustomer.createCustomer(customer),HttpStatus.OK);
	}
	
	@PutMapping("/{id_customer}/image")
	public ResponseEntity<ApiResponse> updateCustomerImage(@PathVariable("id_customer") Integer id_customer, @Valid @RequestBody CustomerImage customerImage, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcCustomer.updateCustomerImage(customerImage.getImage(), id_customer),HttpStatus.OK);
	}
	
	@PutMapping("/{id_customer}/region")
	public ResponseEntity<ApiResponse> updateCustomerRegion(@PathVariable("id_customer") Integer id_customer, @Valid @RequestBody Region region, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcCustomer.updateCustomerRegion(region.getId_region(), id_customer),HttpStatus.OK);
	}
	
	@PutMapping("/{id_customer}")
	public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("id_customer") Integer id_customer, @Valid @RequestBody Customer customer, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcCustomer.updateCustomer(customer, id_customer),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_customer}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("id_customer") Integer id_customer){
		return new ResponseEntity<>(svcCustomer.deleteCustomer(id_customer), HttpStatus.OK);
	}
}
