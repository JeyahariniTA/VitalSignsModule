package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.convertor.VitalSignsConverter;
import com.example.model.VitalSigns;
import com.example.model.VitalSignsDto;
import com.example.repository.VitalSignsRepository;

@Service
public class VitalSignsServiceImpl implements VitalSignsService {

	@Autowired
	VitalSignsRepository vitalSignRepository;

	VitalSignsConverter vitalSignConverter;

	@Override
	public List<VitalSignsDto> listVitalSigns() {
		return vitalSignConverter.convertVitalSignsToDtos(vitalSignRepository.findAll());
	}

	@Override
	public VitalSignsDto getVitalSignById(int id) {
		try {
			if (vitalSignRepository.existsById(id)) {
				return vitalSignConverter.convertVitalSignToDto(vitalSignRepository.findById(id).get());
			} else {
				return new VitalSignsDto();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public VitalSignsDto addVitalSign(VitalSigns vitalSign) {
		return vitalSignConverter.convertVitalSignToDto(vitalSignRepository.save(vitalSign));
	}

	@Override
	public String deleteVitalSign(int id) {
		if (vitalSignRepository.existsById(id)) {
			vitalSignRepository.deleteById(id);
			return "Deleted Successfully!";
		} else {
			return "Please provide valid id to delete.";
		}
	}

	@Override
	public VitalSignsDto updateById(VitalSigns vitalSign) {
		if (vitalSignRepository.existsById(vitalSign.getId())) {
			return vitalSignConverter.convertVitalSignToDto(vitalSignRepository.save(vitalSign));
		} else {
			return new VitalSignsDto();
		}
	}

	@Override
	public List<VitalSignsDto> listVitalSignsWithPagination(int pageNo, int count) {
		List<VitalSignsDto> list = new ArrayList<>();
		PageRequest pageReq = PageRequest.of(pageNo, count);
		list = vitalSignConverter.convertVitalSignsToDtos(vitalSignRepository.findAll(pageReq).getContent());
		return list;
	}

	@Override
	public VitalSignsDto partialUpdate(int id, String temperature) {
		VitalSignsDto vitalSignDto = new VitalSignsDto();
		if (vitalSignRepository.existsById(id)) {
			VitalSigns vitalSign = vitalSignRepository.findById(id).get();
			vitalSign.setHeight(temperature);
			vitalSignDto = vitalSignConverter.convertVitalSignToDto(vitalSignRepository.save(vitalSign));
		}
		return vitalSignDto;
	}

}
