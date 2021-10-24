package com.dwf20221api.api.customer.controller;

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
import com.dwf20221api.api.customer.entity.Region;
import com.dwf20221api.api.customer.service.SvcRegion;
import com.dwf20221api.exceptionHandling.ApiException;

@RestController
@RequestMapping("/region")
public class CtrlRegion {

	@Autowired
	SvcRegion svcRegion;
	
	@GetMapping
	public ResponseEntity<List<Region>> getRegions(){
		return new ResponseEntity<>(svcRegion.getRegions(), HttpStatus.OK);
	}
	
	@GetMapping("/{id_region}")
	public ResponseEntity<Region> getRegion(@PathVariable("id_region") int id){
		return new ResponseEntity<>(svcRegion.getRegion(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createRegion(@Valid @RequestBody Region region, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcRegion.createRegion(region),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id_region}")
	public ResponseEntity<ApiResponse> updateRegion(@PathVariable("id_region") Integer id_region, @Valid @RequestBody Region region, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcRegion.updateRegion(region, id_region),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_region}")
	public ResponseEntity<ApiResponse> deleteRegion(@PathVariable("id_region") Integer id_region){
		return new ResponseEntity<>(svcRegion.deleteRegion(id_region), HttpStatus.OK);
	}
}
