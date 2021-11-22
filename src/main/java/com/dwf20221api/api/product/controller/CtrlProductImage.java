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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.product.entity.ProductImage;
import com.dwf20221api.api.product.service.SvcProductImage;
import com.dwf20221api.exceptionHandling.ApiException;

@RestController
@RequestMapping("/product-image")
public class CtrlProductImage {
	
	@Autowired
	SvcProductImage svcProductImage;

	@GetMapping("/{id_product}")
	public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable("id_product") Integer id_product){
		return new ResponseEntity<>(svcProductImage.getProductImages(id_product), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> uploadProductImage(@Valid @RequestBody ProductImage productImage, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcProductImage.createProductImage(productImage),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_product_image}")
	public ResponseEntity<ApiResponse> deleteProductImage(@PathVariable("id_product_image") Integer id_product_image){
		return new ResponseEntity<>(svcProductImage.deleteProductImage(id_product_image), HttpStatus.OK);
	}
}
