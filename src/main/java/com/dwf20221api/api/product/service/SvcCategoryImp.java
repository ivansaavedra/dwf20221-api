package com.dwf20221api.api.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.product.entity.Category;
import com.dwf20221api.api.product.repository.RepoCategory;
import com.dwf20221api.exceptionHandling.ApiException;

@Service
public class SvcCategoryImp implements SvcCategory {

	@Autowired
	RepoCategory repoCategory;
	
	@Override
	public List<Category> getCategories() {
		try {
			return repoCategory.findByStatus(1);
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public Category getCategory(Integer id_category) {
		try {
			return repoCategory.findById(id_category).get();
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse createCategory(Category category) {
		try {
			Category r = repoCategory.getCategoryByName(category.getCategory());
			if(r != null) {
				repoCategory.updateExistingCategory(category.getCategory());
				return new ApiResponse("category updated");
			}
			category.setStatus(1);
			repoCategory.save(category);
			return new ApiResponse("category created");
		}catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				DataIntegrityViolationException ex = (DataIntegrityViolationException) e;
				if (ex.getMostSpecificCause().toString().contains("category"))
					throw new ApiException(HttpStatus.NOT_FOUND, "category alredy exists");
			}
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse updateCategory(Category category, Integer id_category) {
		try {
			Category r = repoCategory.getCategoryByName(category.getCategory());
			if(r != null) {
				repoCategory.updateExistingCategory(category.getCategory());
				return new ApiResponse("category updated");
			}
			category.setId_category(id_category);
			category.setStatus(1);
			repoCategory.save(category);
			return new ApiResponse("category updated");
		}catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				DataIntegrityViolationException ex = (DataIntegrityViolationException) e;
				if (ex.getMostSpecificCause().toString().contains("category"))
					throw new ApiException(HttpStatus.NOT_FOUND, "category alredy exists");
			}
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse deleteCategory(Integer id_category) {
		try {
			repoCategory.deleteById(id_category);
			return new ApiResponse("category removed");
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

}
