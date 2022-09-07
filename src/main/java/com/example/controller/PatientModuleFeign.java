package com.example.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "PatientModuleFeign", url = "http://localhost:8090/patient")
@FeignClient(name = "patient-module")
public interface PatientModuleFeign {

	@GetMapping("/patient/getByMrn/{mrn}")
	public ResponseEntity<String> getPatientByMrn(@PathVariable int mrn);

	@GetMapping("/patient/get/{id}")
	public ResponseEntity<String> getPatientById(@PathVariable int id);

	@GetMapping("/patient/get")
	public ResponseEntity<String> getPatients();
}
