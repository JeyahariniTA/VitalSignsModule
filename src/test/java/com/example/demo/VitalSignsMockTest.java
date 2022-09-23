package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.model.VitalSigns;
import com.example.model.VitalSignsDto;
import com.example.repository.VitalSignsRepository;
import com.example.service.VitalSignsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class VitalSignsMockTest {

	@InjectMocks
	private VitalSignsServiceImpl vitalSignsServiceImpl;

	@Mock
	private VitalSignsRepository vitalSignsRepository;

	@Mock
	private VitalSigns vitalSigns;

	@BeforeEach
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Order(1)
	@DisplayName("Vitalsigns add logic test with valid data")
	public void addVitalSignsTest() {

		System.out.println("in add vital sign test");
		vitalSigns = new VitalSigns();
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

		Mockito.when(vitalSignsRepository.save(vitalSigns)).thenReturn(vitalSigns);
		VitalSigns created = vitalSignsServiceImpl.addVitalSign(vitalSigns);
		assertThat(created.getBloodPressure()).isSameAs(vitalSigns.getBloodPressure());
		verify(vitalSignsRepository, times(1)).save(vitalSigns);

	}

	@Test
	@Order(2)
	public void getVitalSignsByIdTest() {
		System.out.println("in add vital sign test");
		vitalSigns = new VitalSigns();
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
		List<VitalSigns> vitalSignsList = new ArrayList<>();
		vitalSignsList.add(vitalSigns);
		Mockito.when(vitalSignsRepository.findAll()).thenReturn(vitalSignsList);
		List<VitalSigns> expected = vitalSignsServiceImpl.listVitalSigns();
		assertEquals("200", expected.get(0).getPt());
		verify(vitalSignsRepository, times(1)).findAll();
	}
	
	

}
