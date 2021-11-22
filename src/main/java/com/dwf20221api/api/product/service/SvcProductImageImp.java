package com.dwf20221api.api.product.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.product.entity.ProductImage;
import com.dwf20221api.api.product.repository.RepoProductImage;
import com.dwf20221api.exceptionHandling.ApiException;

@Service
public class SvcProductImageImp implements SvcProductImage {
	
	@Autowired
	RepoProductImage repo;

	@Override
	public List<ProductImage> getProductImages(Integer id_product) {
		try {
			List<ProductImage> images = repo.findByStatus(id_product);
			return images;
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse createProductImage(ProductImage productImage) {
		try {
			if(repo.countImages(productImage.getId_product()) >= 4) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "you have exceeded the limit of images");
			}
			
			long timeMilli = new Date().getTime();
			String src = "C:/Users/ivan-/Documents/UNAM/FCiencias/Docencia/2022-1/Seminario A  - Frontend/GUI/dwfmarket/src/assets/product_images/"; //CAMBIAR
			String file = productImage.getId_product() + "/img_" + timeMilli + ".bmp";
			
			File directorio = new File(src + "/" + productImage.getId_product());
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                System.out.println("Directorio creado");
	            } else {
	                System.out.println("Error al crear directorio");
	            }
	        }

			byte[] data = Base64.getMimeDecoder().decode(productImage.getImage().substring(productImage.getImage().indexOf(",")+1, productImage.getImage().length()));
			try (OutputStream stream = new FileOutputStream(src + file)) {
			    stream.write(data);
			}
			
			productImage.setStatus(1);
			productImage.setImage("product_images/" + file);
			repo.save(productImage);
			return new ApiResponse("image created");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

	@Override
	public ApiResponse deleteProductImage(Integer id_product_image) {
		try {
			repo.deleteProductImage(id_product_image);
			return new ApiResponse("image removed");
		} catch (Exception e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
		}
	}

}
