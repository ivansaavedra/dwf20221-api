package com.dwf20221api.api.invoice.service;

import java.util.List;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.invoice.entity.Cart;

public interface SvcCart {

	public List<Cart> getCart(String rfc);
	public ApiResponse addToCart(Cart cart);
	public ApiResponse removeFromCart(Integer id_cart);
	public ApiResponse deleteCart(String rfc);

}
