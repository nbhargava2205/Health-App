package com.foo.healthApp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foo.healthApp.dao.dto.EnrolleeDto;
import com.foo.healthApp.domain.Enrollee;
import com.foo.healthApp.exception.BeException;
import com.foo.healthApp.repository.DependentRepo;
import com.foo.healthApp.repository.EnrolleRepo;
import com.foo.healthApp.util.Mapper;

@Component
public class EnrolleeDaoImpl implements EnrolleeDao {
	
	@Autowired
	private EnrolleRepo enrolleeRepo;
	
	@Autowired
	private DependentRepo dependentRepo;

	private boolean checkIfEnroleeExists(Long id) {
		int count =  enrolleeRepo.checkIfExists(id) ;
		return count == 1 ? true : false;
	}
	
	private boolean checkIfDependentExists(Long id) {
		int count =  dependentRepo.checkIfExists(id) ;
		return count == 1 ? true : false;
	}
	
	@Override
	public Enrollee getEnrolleeById(Long id) throws BeException {
		Optional<EnrolleeDto> enrDto = enrolleeRepo.findById(id);
		
		if(enrDto.isPresent()) { 
			return Mapper.dto2Pojo(enrDto.get());

		} else {
			throw new BeException( "No records found for " + id , null);
		}
	}
	
	

	@Override
	public void deleteEnrollee(Long id) throws BeException {
		if(checkIfEnroleeExists(id)) {
			enrolleeRepo.deleteById(id);								
		} else {
			throw new BeException( "No records found for " + id , null);
		}

	}

	@Override
	public Long insertEnrollee(Enrollee enrollee) throws BeException {

		EnrolleeDto dto = Mapper.pojo2Dto(enrollee); 
		dto = enrolleeRepo.save(dto);		
		if(dto == null) {
			throw new BeException( "Error adding the enrolee" , null);
		} else {
			return dto.getId();			
		}

	}

	@Override
	public Enrollee updateEnrollee(Enrollee enrollee) throws BeException {
		
		if(checkIfEnroleeExists(enrollee.getId())) {
			EnrolleeDto dto = Mapper.pojo2Dto(enrollee); 
			dto = enrolleeRepo.save(dto);
			return Mapper.dto2Pojo(dto);			
		} else {
			throw new BeException("Cannot update. No records found for " + enrollee.getId() , null);			
		}

	}

	@Override
	public void deleteDependent(Long id) throws BeException {
		if(checkIfDependentExists(id)) {
			dependentRepo.deleteById(id);								
		} else {
			throw new BeException( "No records found for " + id , null);
		}
		
	}

}
