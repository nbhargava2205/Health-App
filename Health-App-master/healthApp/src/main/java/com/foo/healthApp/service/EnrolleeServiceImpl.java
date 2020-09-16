
package com.foo.healthApp.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.foo.healthApp.dao.EnrolleeDao;
import com.foo.healthApp.domain.Dependent;
import com.foo.healthApp.domain.Enrollee;
import com.foo.healthApp.exception.BeException;
import com.foo.healthApp.exception.ValidationException;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {
	
	private final String DATE_FORMAT = "dd-MM-yyyy";
	private final String SUCCESSS = "SUCCESSS";
	private final String FAILURE = "FAILURE";
	private final String GENERIC_ERR = "GENERIC ERR";
	
	@Autowired
	private EnrolleeDao enrolleeDao;

	@Override
	public Enrollee getEnrolleeById(Long id) {

		Enrollee response = null;
		try {
			validateGetEnrollee(id);
			response = enrolleeDao.getEnrolleeById(id);
			response.setStatus(SUCCESSS);
			
		} catch (ValidationException ve) {
			response = new Enrollee(FAILURE, ve.getErrMsg());

		} catch (BeException be) {
			response = new Enrollee(FAILURE, be.getErrMsg());
			
		} catch (Exception e) {
			response = new Enrollee(FAILURE, GENERIC_ERR);
		}

		return response;

	}

	@Override
	public Enrollee deleteEnrollee(Long id) {
		Enrollee response = new Enrollee();
		try {
			enrolleeDao.deleteEnrollee(id);
			response.setStatus("SUCESS");
		} catch (BeException be) {
			response = new Enrollee(FAILURE, be.getErrMsg());
		}
		return response;
	}

	@Override
	public Dependent deleteDependent(Long id) {
		Dependent response = new Dependent();
		try {
			enrolleeDao.deleteDependent(id);
			response.setStatus("SUCESS");
		} catch (BeException be) {
			response = new Dependent(FAILURE, be.getErrMsg());
		}
		return response;
	}
	
	@Override
	public Enrollee insertEnrollee(Enrollee enrollee) {
		Enrollee response = new Enrollee();
		try {
			validateInsertEnrollee(enrollee);
			Long id = enrolleeDao.insertEnrollee(enrollee);
			response.setId(id);
			response.setStatus(SUCCESSS);
		} catch (ValidationException ve) {
			response = new Enrollee(FAILURE, ve.getErrMsg());

		} catch (BeException be) {
			response = new Enrollee(FAILURE, be.getErrMsg());

		} catch (Exception e) {
			response = new Enrollee(FAILURE, GENERIC_ERR);
		}

		return response;

	}

	@Override
	public Enrollee updateEnrollee(Enrollee enrollee) {

		Enrollee response = new Enrollee();

		try {
			validateUpdateEnrollee(enrollee);
			response = enrolleeDao.updateEnrollee(enrollee);
			response.setStatus(SUCCESSS);
			
		} catch (ValidationException ve) {
			response = new Enrollee(FAILURE,  ve.getErrMsg());

		} catch (BeException be) {
			response = new Enrollee(FAILURE, be.getErrMsg());
			
		} catch (Exception e) {
			response = new Enrollee(FAILURE, GENERIC_ERR);
		}

		return response;
	}

	private void validateGetEnrollee(Long id) throws ValidationException {
		if (id == null) {
			throw new ValidationException("Id cannot be empty", null);
		}
	}

	public void validateInsertEnrollee(Enrollee enrollee) throws ValidationException {

		if (StringUtils.isEmpty(enrollee.getName())) {
			throw new ValidationException( "Name cannot be empty", null);
		}
		if (StringUtils.isEmpty(enrollee.getActivationStatus())) {
			throw new ValidationException("Activation status cannot be empty", null);
		}
		if (StringUtils.isEmpty(enrollee.getBirthDate())) {
			throw new ValidationException( "Birthdate cannot be empty", null);
		} else {
			validateDate(enrollee.getBirthDate());
		}
		if(!StringUtils.isEmpty(enrollee.getPhoneNumber())) {
			try {
				Long phNumber = Long.parseLong(enrollee.getPhoneNumber());				
			
				if(phNumber < 999999999 || phNumber > 9999999999l) {
					throw new ValidationException("Phone number is not valid", null);
				}
				
			} catch (NumberFormatException ex) {
				throw new ValidationException("Phone number can only be numberic", null);
			}

		}
		
		if (enrollee.getDependents() != null && enrollee.getDependents().size() > 0) {
			for (Dependent dep : enrollee.getDependents()) {
				if (StringUtils.isEmpty(dep.getName())) {
					throw new ValidationException("Dependent Name cannot be empty", null);
				}
				if (StringUtils.isEmpty(dep.getBirthDate())) {
					throw new ValidationException("Dependent BirthDate cannot be empty", null);
				} else {
					validateDate(dep.getBirthDate());
				}
			}
		}
	}

	private void validateUpdateEnrollee(Enrollee enrollee) throws ValidationException {
		if (StringUtils.isEmpty(enrollee.getId())) {
			throw new ValidationException("Id cannot be empty", null);
		}
		validateInsertEnrollee(enrollee);
	}
	
	public void validateDate(String date) throws ValidationException 
	{
	        try {
	            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	            df.setLenient(false);
	            df.parse(date);
	        } catch (Exception e) {

	        	throw new ValidationException("Invalid date. Please follow format [dd-MM-yyyy]", null);
	        }
	}

}
