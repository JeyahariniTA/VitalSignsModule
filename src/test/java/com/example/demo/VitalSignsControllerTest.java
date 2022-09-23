package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.PatientModuleFeign;
import com.example.controller.VitalSignsController;
import com.example.convertor.VitalSignsConverter;
import com.example.model.VitalSigns;
import com.example.model.VitalSignsDto;
import com.example.repository.VitalSignsRepository;
import com.example.service.VitalSignsService;
import com.example.service.VitalSignsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class VitalSignsControllerTest {

	@Mock
	VitalSignsRepository vitalSignsRepository;

	@Mock
	PatientModuleFeign patientModuleFeign;

	@Mock
	VitalSignsConverter vitalSignsConverter;

	@InjectMocks
	VitalSignsController vitalSignsController;

	@Mock
	public VitalSignsService dao;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// dto = new VitalSignsDto();
		dao = new VitalSignsServiceImpl();
	}

	@Test
	@DisplayName("Vitalsigns add logic test with valid data")
	public void addVitalSignsTest() {

		System.out.println("in add vital sign test");
		VitalSigns vitalSigns = new VitalSigns();
		vitalSigns.setId(1);
		vitalSigns.setBloodPressure("120 mm");
		vitalSigns.setBloodSugar("120");
		vitalSigns.setHeight("160 cm");
		vitalSigns.setPt("200");
		vitalSigns.setPulse("70");
		vitalSigns.setRespirations("12");
		vitalSigns.setSpo2Sat("90%");
		vitalSigns.setTemperature("99.1 F");
		vitalSigns.setWeight("60");
		vitalSigns.setPatientId(1);

		VitalSignsDto dto = new VitalSignsDto();

		dto.setId(1);
		dto.setBloodPressure("120 mm");
		dto.setBloodSugar("120");
		dto.setHeight("160 cm");
		dto.setPt("200");
		dto.setPulse("70");
		dto.setRespirations("12");
		dto.setSpo2Sat("90%");
		dto.setTemperature("99.1 F");
		dto.setWeight("60");
		dto.setPatientId(1);

		ResponseEntity<String> resultObj = new ResponseEntity<>("1", HttpStatus.OK);
		Mockito.when(vitalSignsRepository.save(vitalSigns)).thenReturn(vitalSigns);
		Mockito.when(vitalSignsConverter.convertDtoToVitalSigns(dto)).thenReturn(vitalSigns);
		Mockito.when(vitalSignsConverter.convertVitalSignsToDto(vitalSigns)).thenReturn(dto);
		Mockito.when(patientModuleFeign.getPatientByMrn(0)).thenReturn(resultObj);
		Mockito.when(dao.addVitalSign(vitalSigns)).thenReturn(vitalSigns);
		System.out.println("dto blood pressure: " + dto.getBloodPressure());
		ResponseEntity<Object> created = vitalSignsController.addVitalSigns(dto, 0);
		VitalSignsDto resultDto = (VitalSignsDto) created.getBody();
		assertEquals(resultDto.getBloodPressure(), dto.getBloodPressure());

	}

}
