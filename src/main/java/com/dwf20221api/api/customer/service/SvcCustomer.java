package com.dwf20221api.api.customer.service;

import java.util.List;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.customer.entity.Customer;

public interface SvcCustomer {

	public List<Customer> getCustomers();
	public Customer getCustomer(String rfc);
	public ApiResponse createCustomer(Customer customer);
	public ApiResponse updateCustomer(Customer customer, Integer id_customer);
	public ApiResponse updateCustomerImage(String image, Integer id_customer);
	public ApiResponse updateCustomerRegion(Integer id_region, Integer id_customer);
	public ApiResponse deleteCustomer(Integer id_customer);
}
