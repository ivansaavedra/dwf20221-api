package com.dwf20221api.api.product.service;

import java.util.List;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.product.entity.Category;

public interface SvcCategory {

	public List<Category> getCategories();
	public Category getCategory(Integer id_category);
	public ApiResponse createCategory(Category category);
	public ApiResponse updateCategory(Category category, Integer id_category);
	public ApiResponse deleteCategory(Integer id_category);
}
