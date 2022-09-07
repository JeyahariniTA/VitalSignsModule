package com.example.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CustomException;

@RestController
@RequestMapping("/feign")
public class PatientModuleFeignController {

	@Autowired
	private PatientModuleFeign patientModuleFeign;

	@GetMapping("/getPatientByMrn/{mrn}")
	public String getPatientByMrn(@PathVariable int mrn) {
		try {
			ResponseEntity<String> obj = patientModuleFeign.getPatientByMrn(mrn);
			return obj.getBody();
		} catch (CustomException ex) {
			throw new CustomException(ex.getLocalizedMessage(), ex.getStatus());
		}

	}

	@GetMapping("/getPatientById/{id}")
	@ResponseBody
	public String getPatientById(@PathVariable int id) {
		try {
			ResponseEntity<String> obj = patientModuleFeign.getPatientById(id);
			return obj.getBody();
		} catch (CustomException ex) {
			throw new CustomException(ex.getLocalizedMessage(), ex.getStatus());
		}
	}

	@GetMapping("/getPatients")
	@ResponseBody
	public JSONObject getPatients() {
		try {
			ResponseEntity<String> obj = patientModuleFeign.getPatients();
			return new JSONObject(obj.getBody());
		} catch (CustomException ex) {
			throw new CustomException(ex.getLocalizedMessage(), ex.getStatus());
		}
	}

}
