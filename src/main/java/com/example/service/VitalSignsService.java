package com.example.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.example.model.VitalSigns;
import com.example.model.VitalSignsDto;

public interface VitalSignsService {

	@Cacheable(value = "VitalSignsCache")
	public List<VitalSignsDto> listVitalSigns();

	public List<VitalSignsDto> listVitalSignsWithPagination(int pageNo, int vitalSignsCount);

	@Cacheable(value = "VitalSignsCache", key = "#id")
	public VitalSignsDto getVitalSignById(int id);

	public VitalSignsDto addVitalSign(VitalSigns vitalSings);

	@CacheEvict(value = "VitalSignsCache", key = "#id")
	public String deleteVitalSign(int id);

	@CachePut(value = "VitalSignsCache")
	public VitalSignsDto updateById(VitalSigns vitalSigns);

	public VitalSignsDto partialUpdate(int id, String gender);

}
