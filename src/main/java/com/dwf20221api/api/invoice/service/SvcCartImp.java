package com.dwf20221api.api.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.invoice.entity.Cart;
import com.dwf20221api.api.invoice.repository.RepoCart;
import com.dwf20221api.api.product.repository.RepoProduct;
import com.dwf20221api.exceptionHandling.ApiException;

@Service
public class SvcCartImp implements SvcCart {
	
	@Autowired
	RepoCart repo;
	
	@Autowired
	RepoProduct repoProduct;

	@Override
	public List<Cart> getCart(String rfc) {
		try {
			return repo.findByRfc(rfc);
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse addToCart(Cart cart) {
		try {
			Integer product_stock = repoProduct.getStock(cart.getId_product());
			Cart c = repo.findByProduct(cart.getId_product(),cart.getRfc());
			if(c != null) {
				if(c.getQuantity()+cart.getQuantity() > product_stock) {
					throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "invalid quantity");
				}
				System.out.println(c.getQuantity());
				System.out.println(cart.getQuantity());
				repo.updateQuantity(c.getId_cart(), c.getQuantity()+cart.getQuantity());
				return new ApiResponse("quantity updated");
			}
			
			if(cart.getQuantity() > product_stock) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "invalid quantity");
			}
			
			cart.setStatus(1);
			repo.save(cart);
			return new ApiResponse("item added");
		}catch(Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse removeFromCart(Integer id_cart) {
		try {
			repo.removeFromCart(id_cart);
			return new ApiResponse("item removed");
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse deleteCart(String rfc) {
		try {
			repo.deleteCart(rfc);
			return new ApiResponse("cart removed");
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

}
