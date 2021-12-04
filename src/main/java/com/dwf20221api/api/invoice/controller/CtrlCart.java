package com.dwf20221api.api.invoice.controller;

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
import com.dwf20221api.api.invoice.entity.Cart;
import com.dwf20221api.api.invoice.service.SvcCart;
import com.dwf20221api.exceptionHandling.ApiException;

@RestController
@RequestMapping("/cart")
public class CtrlCart {

	@Autowired
	SvcCart svcCart;

	@GetMapping("/{rfc}")
	public ResponseEntity<List<Cart>> getCart(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcCart.getCart(rfc), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> addToCart(@Valid @RequestBody Cart cart, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcCart.addToCart(cart),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id_cart}")
	public ResponseEntity<ApiResponse> removeFromCart(@PathVariable("id_cart") Integer id_cart){
		return new ResponseEntity<>(svcCart.removeFromCart(id_cart), HttpStatus.OK);
	}
	
	@DeleteMapping("clear/{rfc}")
	public ResponseEntity<ApiResponse> deleteCart(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcCart.deleteCart(rfc), HttpStatus.OK);
	}
}
