package com.dwf20221api.api.product.service;

import java.util.List;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.product.entity.ProductImage;

public interface SvcProductImage {
	
	public List<ProductImage> getProductImages(Integer id_product);
	public ApiResponse createProductImage(ProductImage productImage);
	public ApiResponse deleteProductImage(Integer id_product_image);

}
