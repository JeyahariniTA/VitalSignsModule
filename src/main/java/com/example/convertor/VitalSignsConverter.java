package com.example.convertor;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import com.example.model.VitalSigns;
import com.example.model.VitalSignsDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface VitalSignsConverter {

	VitalSignsDto convertVitalSignsToDto(VitalSigns vitalSigns);

	VitalSigns convertDtoToVitalSigns(VitalSignsDto dto);

	List<VitalSignsDto> convertVitalSignsToDtos(List<VitalSigns> vitalSigns);

	List<VitalSigns> convertDtosToVitalSigns(List<VitalSignsDto> dtos);

}
