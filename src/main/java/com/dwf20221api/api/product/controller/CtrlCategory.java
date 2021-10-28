package com.dwf20221api.api.product.controller;

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
import com.dwf20221api.api.product.entity.Category;
import com.dwf20221api.api.product.service.SvcCategory;
import com.dwf20221api.exceptionHandling.ApiException;

@RestController
@RequestMapping("/category")
public class CtrlCategory {

	@Autowired
	SvcCategory svcCategory;
	
	@GetMapping
	public ResponseEntity<List<Category>> getCategories(){
		return new ResponseEntity<>(svcCategory.getCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/{id_category}")
	public ResponseEntity<Category> getCategory(@PathVariable("id_category") int id){
		return new ResponseEntity<>(svcCategory.getCategory(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcCategory.createCategory(category),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id_category}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id_category") Integer id_category, @Valid @RequestBody Category category, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcCategory.updateCategory(category, id_category),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_category}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id_category") Integer id_category){
		return new ResponseEntity<>(svcCategory.deleteCategory(id_category), HttpStatus.OK);
	}
}
