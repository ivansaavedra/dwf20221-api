package com.dwf20221api.api.product.service;

import java.util.List;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.product.dto.DtoProductCategory;
import com.dwf20221api.api.product.dto.DtoProductList;
import com.dwf20221api.api.product.entity.Product;

public interface SvcProduct {
	
	public List<DtoProductList> getProducts();
	public Product getProduct(String gtin);
	public ApiResponse createProduct(Product product);
	public ApiResponse updateProduct(Product product, Integer id_product);
	public ApiResponse updateProductCategory(Integer id_category, Integer id_product);
	public ApiResponse deleteProduct(Integer id_product);

	public List<DtoProductCategory> getProductsRandom();
	public List<DtoProductCategory> getProductsCategory(Integer id_category);
}
