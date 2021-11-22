package com.dwf20221api.api.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.product.dto.DtoProductList;
import com.dwf20221api.api.product.entity.Product;
import com.dwf20221api.api.product.repository.RepoProduct;
import com.dwf20221api.api.product.repository.RepoProductList;
import com.dwf20221api.exceptionHandling.ApiException;

@Service
public class SvcProductImp implements SvcProduct {
	
	@Autowired
	RepoProduct repoProduct;
	
	@Autowired
	RepoProductList repoProductList;

	@Autowired
	SvcCategory svcCategory;

	@Override
	public List<DtoProductList> getProducts() {
		try {
			List<DtoProductList> products = repoProductList.findByStatus();
			return products;
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public Product getProduct(String gtin) {
		try {
			Product product = repoProduct.findByGtin(gtin);
			return product;
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse createProduct(Product product) {
		try {
			product.setStatus(1);
			repoProduct.save(product);
			return new ApiResponse("product created");
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				DataIntegrityViolationException ex = (DataIntegrityViolationException) e;
				if (ex.getMostSpecificCause().toString().contains("id_category"))
					throw new ApiException(HttpStatus.NOT_FOUND, "id_category does not exists");
				if (ex.getMostSpecificCause().toString().contains("gtin"))
					throw new ApiException(HttpStatus.NOT_FOUND, "gtin alredy exists");
			}
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse updateProduct(Product product, Integer id_product) {
		try {
			if (repoProduct.updateProduct(id_product, product.getGtin(), product.getProduct(), product.getDescription(), product.getPrice(), product.getStock()) > 0)
				return new ApiResponse("product updated");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "product cannot be updated");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse updateProductCategory(Integer id_category, Integer id_product) {
		try {
			if (repoProduct.updateProductCategory(id_category, id_product) > 0)
				return new ApiResponse("product category updated");
			else
				throw new ApiException(HttpStatus.NOT_FOUND, "product or category not found");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse deleteProduct(Integer id_product) {
		try {
			if (repoProduct.deleteProduct(id_product) > 0)
				return new ApiResponse("product removed");
			else
				throw new ApiException(HttpStatus.NOT_FOUND, "product not found");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

}
