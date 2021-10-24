package com.dwf20221api.api.customer.service;

import java.util.List;

import com.dwf20221api.api.customer.dto.ApiResponse;
import com.dwf20221api.api.customer.entity.Region;

public interface SvcRegion {

	public List<Region> getRegions();
	public Region getRegion(Integer id_region);
	public ApiResponse createRegion(Region region);
	public ApiResponse updateRegion(Region region, Integer id_region);
	public ApiResponse deleteRegion(Integer id_region);
}
