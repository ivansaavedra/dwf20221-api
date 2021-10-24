package com.dwf20221api.api.customer.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.customer.entity.Customer;
import com.dwf20221api.api.customer.entity.CustomerImage;
import com.dwf20221api.api.customer.repository.RepoCustomer;
import com.dwf20221api.exceptionHandling.ApiException;

@Service
public class SvcCustomerImp implements SvcCustomer {

	@Autowired
	RepoCustomer repoCustomer;

	@Autowired
	SvcRegion svcRegion;

	@Override
	public List<Customer> getCustomers() {
		try {
			List<Customer> customers = repoCustomer.findByStatus(1);
			if (customers != null) {
				for (Customer customer : customers) {
					customer.setImage(customer.getCustomerImage().getImage());
				}
			}
			return customers;
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public Customer getCustomer(String rfc) {
		try {
			Customer customer = repoCustomer.findByRfc(rfc);
			if (customer != null) {
				customer.setImage(customer.getCustomerImage().getImage());
			}
			return customer;
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse createCustomer(Customer customer) {
		try {
			CustomerImage image = new CustomerImage();
			image.setImage((customer.getImage() == null) ? "" : customer.getImage());
			image.setStatus(1);
			customer.setCustomerImage(image);
			customer.setStatus(1);
			repoCustomer.save(customer);
			return new ApiResponse("customer created");
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				DataIntegrityViolationException ex = (DataIntegrityViolationException) e;
				if (ex.getMostSpecificCause().toString().contains("id_region"))
					throw new ApiException(HttpStatus.NOT_FOUND, "id_region does not exists");
				if (ex.getMostSpecificCause().toString().contains("rfc"))
					throw new ApiException(HttpStatus.NOT_FOUND, "rfc alredy exists");
			}
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse updateCustomer(@Valid Customer customer, Integer id_customer) {
		try {
			if (repoCustomer.updateCustomer(id_customer, customer.getName(), customer.getSurname(), customer.getRfc(),
					customer.getMail(), customer.getAddress()) > 0)
				return new ApiResponse("customer updated");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer cannot be updated");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse updateCustomerImage(String image, Integer id_customer) {
		try {
			if (repoCustomer.updateCustomerImage(image, id_customer) > 0)
				return new ApiResponse("customer image updated");
			else
				throw new ApiException(HttpStatus.NOT_FOUND, "customer not found");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse updateCustomerRegion(Integer id_region, Integer id_customer) {
		try {
			if (repoCustomer.updateCustomerRegion(id_region, id_customer) > 0)
				return new ApiResponse("customer region updated");
			else
				throw new ApiException(HttpStatus.NOT_FOUND, "customer or region not found");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse deleteCustomer(Integer id_customer) {
		try {
			if (repoCustomer.deleteByIdCustomer(id_customer) > 0)
				return new ApiResponse("customer removed");
			else
				throw new ApiException(HttpStatus.NOT_FOUND, "customer not found");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

}
