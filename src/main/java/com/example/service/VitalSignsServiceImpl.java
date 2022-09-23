package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.config.LogAuditPayload;
import com.example.config.LogExecutionTime;
import com.example.convertor.VitalSignsConverter;
import com.example.model.VitalSigns;
import com.example.model.VitalSignsDto;
import com.example.repository.VitalSignsRepository;

@Service
public class VitalSignsServiceImpl implements VitalSignsService {

	@Autowired
	VitalSignsRepository vitalSignRepository;

	@Autowired
	VitalSignsConverter vitalSignConverter;

	@Override
	public List<VitalSigns> listVitalSigns() {
		return vitalSignRepository.findAll();
	}

	@Override
	@LogExecutionTime
	public VitalSignsDto getVitalSignById(int id) {
		try {
			if (vitalSignRepository.existsById(id)) {
				return vitalSignConverter.convertVitalSignsToDto(vitalSignRepository.findById(id).get());
			} else {
				return new VitalSignsDto();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@LogAuditPayload(action = "add", description = "Adding vital signs")
	public VitalSigns addVitalSign(VitalSigns vitalSign) {
		return vitalSignRepository.save(vitalSign);
	}

	@Override
	@LogAuditPayload(action = "delete", description = "Deleting vital sing")
	public String deleteVitalSign(int id) {
		if (vitalSignRepository.existsById(id)) {
			vitalSignRepository.deleteById(id);
			return "Deleted Successfully!";
		} else {
			return "Please provide valid id to delete.";
		}
	}

	@Override
	@LogAuditPayload(action = "update", description = "Updating vital signs")
	public VitalSignsDto updateById(VitalSigns vitalSign) {
		if (vitalSignRepository.existsById(vitalSign.getId())) {
			return vitalSignConverter.convertVitalSignsToDto(vitalSignRepository.save(vitalSign));
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
			vitalSignDto = vitalSignConverter.convertVitalSignsToDto(vitalSignRepository.save(vitalSign));
		}
		return vitalSignDto;
	}

}
