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
import com.dwf20221api.api.product.dto.DtoProductCategory;
import com.dwf20221api.api.product.dto.DtoProductList;
import com.dwf20221api.api.product.entity.Category;
import com.dwf20221api.api.product.entity.Product;
import com.dwf20221api.api.product.service.SvcProduct;
import com.dwf20221api.config.security.annotation.AllowedRoles;
import com.dwf20221api.exceptionHandling.ApiException;

@RestController
@RequestMapping("/product")
public class CtrlProduct {
	
	@Autowired
	SvcProduct svcProduct;
	
	/*
	 * La anotacion allowed roles es la que controlara que roles de usuarios pueden solicitar 
	 * el endpoint y los servicios asociados.
	 */
	@GetMapping
	@AllowedRoles("ADMIN_USER")
	public ResponseEntity<List<DtoProductList>> getProducts(){
		return new ResponseEntity<>(svcProduct.getProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/{gtin}")
	public ResponseEntity<Product> getProduct(@PathVariable("gtin") String gtin){
		return new ResponseEntity<>(svcProduct.getProduct(gtin), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcProduct.createProduct(product),HttpStatus.OK);
	}
	
	@PutMapping("/{id_product}/category")
	public ResponseEntity<ApiResponse> updateProductCategory(@PathVariable("id_product") Integer id_product, @Valid @RequestBody Category category, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcProduct.updateProductCategory(category.getId_category(), id_product),HttpStatus.OK);
	}
	
	@PutMapping("/{id_product}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id_product") Integer id_product, @Valid @RequestBody Product product, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcProduct.updateProduct(product, id_product),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_product}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id_product") Integer id_product){
		return new ResponseEntity<>(svcProduct.deleteProduct(id_product), HttpStatus.OK);
	}
	
	@GetMapping("/random")
	public ResponseEntity<List<DtoProductCategory>> getProductsRandom(){
		return new ResponseEntity<>(svcProduct.getProductsRandom(), HttpStatus.OK);
	}
	
	@GetMapping("category/{id_category}")
	public ResponseEntity<List<DtoProductCategory>> getProductsCategory(@PathVariable("id_category") Integer id_category){
		return new ResponseEntity<>(svcProduct.getProductsCategory(id_category), HttpStatus.OK);
	}
}
