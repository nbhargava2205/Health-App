package com.foo.healthApp.util;

import java.util.List;
import java.util.stream.Collectors;

import com.foo.healthApp.dao.dto.DependentDto;
import com.foo.healthApp.dao.dto.EnrolleeDto;
import com.foo.healthApp.domain.Dependent;
import com.foo.healthApp.domain.Enrollee;

public class Mapper {
	
	public static Enrollee dto2Pojo(EnrolleeDto dto) {
		Enrollee enrollee = new Enrollee();
		enrollee.setId(dto.getId());
		enrollee.setName(dto.getName());
		enrollee.setActivationStatus(dto.getActivationStatus());
		enrollee.setPhoneNumber(dto.getPhoneNumber());
		enrollee.setBirthDate(dto.getBirthDate());
		if(dto.getDependents()!= null && dto.getDependents().size()>0) {
			List<Dependent> dependents= dto.getDependents().stream().map(d->dto2Pojo(d) ).collect(Collectors.toList());
			enrollee.setDependents(dependents);
		}
		return enrollee;
	}
	
	public static Dependent dto2Pojo(DependentDto dto) {
		Dependent d = new Dependent();
		d.setId(dto.getId());
		d.setBirthDate(dto.getBirthDate());
		d.setName(dto.getName());
		return d;
	}
	
	public static EnrolleeDto pojo2Dto(Enrollee e) {
		EnrolleeDto dto = new EnrolleeDto();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setActivationStatus(e.getActivationStatus());
		dto.setPhoneNumber(e.getPhoneNumber());
		dto.setBirthDate(e.getBirthDate());
		if(e.getDependents()!= null && e.getDependents().size()>0) {
			List<DependentDto> dependents= e.getDependents().stream().map(d->pojo2Dto(d) ).collect(Collectors.toList());
			dependents.stream().forEach(d-> d.setParentEnrolee(dto));
			dto.setDependents(dependents);
		}
		return dto;
	}
	
	public static DependentDto pojo2Dto(Dependent d) {
		DependentDto dto = new DependentDto();
		dto.setId(d.getId());
		dto.setBirthDate(d.getBirthDate());
		dto.setName(d.getName());
		return dto;
	}

}
