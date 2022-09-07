package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.convertor.VitalSignsConverter;
import com.example.exception.CustomException;
import com.example.model.VitalSigns;
import com.example.model.VitalSignsDto;
import com.example.service.VitalSignsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/vitalsigns")
@SecurityRequirement(name = "bearerAuth")
public class VitalSignsController {
	@Autowired
	VitalSignsService dao;

	VitalSignsConverter vitalSignsConverter;

	@Autowired
	PatientModuleFeign patientModuleFeign;

	@GetMapping(path = "/Hello")
	@ResponseBody
	public String sayHello() {
		return "Hello!";
	}

	@GetMapping(path = "/get")
	@ResponseBody
	public List<VitalSignsDto> getVitalSigns() {
		return dao.listVitalSigns();
	}

	@GetMapping(path = "/get/{vitalSignId}")
	@ResponseBody
	public VitalSignsDto getVitalSignsById(@PathVariable("vitalSignId") int vitalSignId) {
		return dao.getVitalSignById(vitalSignId);
	}

	@GetMapping(path = "/get/{pageNo}/{count}")
	@ResponseBody
	public List<VitalSignsDto> getVitalSignsPatientsWithPagination(@PathVariable("pageNo") int pageNo,
			@PathVariable("count") int count) {
		return dao.listVitalSignsWithPagination(pageNo, count);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Object> addVitalSigns(@RequestBody VitalSignsDto vitalSignsDto,
			@RequestParam("mrn") int mrn) {

		try {
			ResponseEntity<String> obj = patientModuleFeign.getPatientByMrn(mrn);
			String id = obj.getBody();
			vitalSignsDto.setPatientId(Integer.parseInt(id));
			vitalSignsDto = dao.addVitalSign(vitalSignsConverter.convertDtoToVitalSign(vitalSignsDto));

			if (vitalSignsDto.getId() == 0) {
				throw new CustomException("Failed to add vital signs", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(vitalSignsDto, HttpStatus.OK);
			}
		} catch (CustomException e) {
			throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/delete/{vitalSignId}")
	public String deletePatient(@PathVariable("vitalSignId") int vitalSignId) {
		return dao.deleteVitalSign(vitalSignId);

	}

	@PutMapping(path = "/update")
	public ResponseEntity<Object> updateVitalSignById(@RequestBody VitalSigns vitalSign) {

		VitalSignsDto dto = dao.updateById(vitalSign);
		if (dto.getId() == 0) {
			throw new CustomException("Vital Sign not exists", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}

	}

	@PatchMapping("/update/{id}/{height}")
	public ResponseEntity<Object> updatePartially(@PathVariable("id") int id, @PathVariable("height") String height) {
		VitalSignsDto dto = dao.partialUpdate(id, height);
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

}
