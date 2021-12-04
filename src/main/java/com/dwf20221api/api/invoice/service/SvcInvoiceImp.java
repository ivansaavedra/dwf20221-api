package com.dwf20221api.api.invoice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.invoice.dto.ProductCart;
import com.dwf20221api.api.invoice.entity.Cart;
import com.dwf20221api.api.invoice.entity.Invoice;
import com.dwf20221api.api.invoice.entity.Item;
import com.dwf20221api.api.invoice.repository.RepoCart;
import com.dwf20221api.api.invoice.repository.RepoInvoice;
import com.dwf20221api.api.invoice.repository.RepoItem;
import com.dwf20221api.exceptionHandling.ApiException;

@Service
public class SvcInvoiceImp implements SvcInvoice {

	@Autowired
	RepoInvoice repo;
	
	@Autowired
	RepoItem repoItem;
	
	@Autowired
	RepoCart repoCart;

	@Override
	public List<Invoice> getInvoices(String rfc) {
		try {
			return repo.getInvoices(rfc);
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

	@Override
	public List<Item> getItems(Integer id_invoice) {
		try {
			return repoItem.getItems(id_invoice);
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}
	
	@Override
	public ApiResponse purchase(String rfc) {
		Double subtotal = 0.0;
		Double taxes = 0.0;
		Double total = 0.0;
		Double item_subtotal = 0.0;
		Double item_taxes = 0.0;
		Double item_total = 0.0;
		LocalDate date = LocalDate.now();
		Item item;
		ProductCart product;
		try {
			System.out.println(rfc);
			List<Cart> cart = repoCart.findByRfc(rfc);
			if(cart.size() == 0) {
				throw new ApiException(HttpStatus.NOT_FOUND, "cart not found");
			}
			
			Invoice invoice = new Invoice();
			invoice.setRfc(rfc);
			invoice.setSubtotal(subtotal);
			invoice.setTaxes(taxes);
			invoice.setTotal(total);
			invoice.setCreated_at(date);
			invoice.setStatus(0);
			
			Integer id_invoice = repo.save(invoice).getId_invoice();
			System.out.println(id_invoice);
			
			for(int i=0; i<cart.size(); i++) {
				product = cart.get(i).getProduct();
				
				item = new Item();
				item_subtotal = 0.0;
				item_taxes = 0.0;
				item_total = 0.0;
				
				item.setId_invoice(id_invoice);
				item.setGtin(product.getGtin());
				item.setQuantity(cart.get(i).getQuantity());
				item.setUnit_price(product.getPrice());
				item_total = product.getPrice() * cart.get(i).getQuantity();
				item_subtotal = item_total * 0.84;
				item_taxes = item_total * 0.16;
				item.setSubtotal(item_subtotal);
				item.setTaxes(item_taxes);
				item.setTotal(item_total);
				item.setStatus(1);
				
				repoItem.save(item);

				total += cart.get(i).getQuantity() * product.getPrice();
			}
			
			subtotal = total * 0.84;
			taxes = total *0.16;
			
			repo.updateInvoice(id_invoice, subtotal, taxes, total);
			
			repoCart.deleteCart(rfc);
			
			return new ApiResponse("invoice created");
		}catch(Exception e) {
			throw new ApiException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		}
	}

}
