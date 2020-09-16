package com.foo.healthApp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foo.healthApp.dao.EnrolleeDao;
import com.foo.healthApp.domain.Dependent;
import com.foo.healthApp.domain.Enrollee;
import com.foo.healthApp.exception.BeException;
import com.foo.healthApp.exception.ValidationException;

@ExtendWith(SpringExtension.class)
public class EnrolleeServiceImplTest {
	
	
	@TestConfiguration
    static class EnrolleeServiceImplTestContextConfiguration {
 
        @Bean
        public EnrolleeServiceImpl enrolleeServiceImpl() {
            return new EnrolleeServiceImpl();
        }
    }
 
    @Autowired
    private EnrolleeServiceImpl enrolleeServiceImpl;
    
    @MockBean
	private EnrolleeDao enrolleeDao;
        
    
    @Test
    public void getEnrolleeByIdTest_nullId() {
    	Enrollee e  = enrolleeServiceImpl.getEnrolleeById(null);
    	assertEquals("Id cannot be empty", e.getErrorMsg());        	
    }
    
    @Test
    public void getEnrolleeByIdTest_beErr() {
    	
    	Long id = 1234l;
    	try {
			Mockito.when(enrolleeDao.getEnrolleeById(id)).thenThrow(new BeException( "No records found for " + id , null));
		} catch (BeException e1) {

		}
    	Enrollee e  = enrolleeServiceImpl.getEnrolleeById(id);
    	assertEquals("No records found for " + id, e.getErrorMsg());        	
    }
    
    @Test
    public void getEnrolleeByIdTest_success() {
    	
    	Long id = 1234l;
		Enrollee resp = new Enrollee();
		resp.setId(id);
		
    	try {
			Mockito.when(enrolleeDao.getEnrolleeById(id)).thenReturn(resp);
		} catch (BeException e1) {

		}
    	Enrollee e  = enrolleeServiceImpl.getEnrolleeById(id);
    	assertEquals( e.getId(), id);	
    }
    
  @Test
  public void deleteEnrolleeTest_beError() {
	  
	  Long id = 1234l;
  	try {
  			Mockito.doThrow(new BeException( "Error adding the enrolee" , null)).when(enrolleeDao).deleteEnrollee(id);
		} catch (BeException e1) {

		}
  	Enrollee e  = enrolleeServiceImpl.deleteEnrollee(id);
  	assertEquals("Error adding the enrolee", e.getErrorMsg()); 
	  
  }
  
  @Test
  public void deleteEnrolleeTest_success() {
	  
	Long id = 1234l;	  
  	Enrollee e  = enrolleeServiceImpl.deleteEnrollee(id);
  	assertEquals(e.getStatus(), "SUCESS"); 
	  
  }
  

  @Test
  public void insertEnrolleeTest_BeErr() {
	  
	Long id = 1234l;	  
	Enrollee enrollee = new Enrollee();
	enrollee.setName("n");
	enrollee.setBirthDate("09-09-2010");
	enrollee.setPhoneNumber("1111111111");
	enrollee.setActivationStatus("true");
	try {
		Mockito.when(enrolleeDao.insertEnrollee(enrollee)).thenThrow(new BeException( "Error adding the enrolee" , null));
	} catch (BeException e) {

	}
	enrollee = enrolleeServiceImpl.insertEnrollee(enrollee);
  	assertEquals(enrollee.getErrorMsg(), "Error adding the enrolee"); 
	  
  }
  
  @Test
  public void insertEnrolleeTest_success() {
	  
	Long id = 1234l;	  
	Enrollee enrollee = new Enrollee();
	enrollee.setName("n");
	enrollee.setBirthDate("09-09-2010");
	enrollee.setPhoneNumber("1111111111");
	enrollee.setActivationStatus("true");
	try {
		Mockito.when(enrolleeDao.insertEnrollee(enrollee)).thenReturn(9l);
	} catch (BeException e) {

	}
	enrollee = enrolleeServiceImpl.insertEnrollee(enrollee);
  	assertEquals("SUCCESSS", enrollee.getStatus());
  	assertTrue(enrollee.getId() == 9l);
	  
  }
  
  @Test
  
  public void updateEnrolleeTest_BeErr() {
	  
		Enrollee enrollee = new Enrollee();
		enrollee.setId(1L);
		enrollee.setName("n");
		enrollee.setBirthDate("09-09-2010");
		enrollee.setPhoneNumber("1111111111");
		enrollee.setActivationStatus("true");
		try {
			Mockito.when(enrolleeDao.updateEnrollee(enrollee)).thenThrow(new BeException( "Cannot update. No records found for " + enrollee.getId(), null));
		} catch (BeException e) {

		}
		Enrollee resp = enrolleeServiceImpl.updateEnrollee(enrollee);
	  	assertEquals(resp.getErrorMsg(), "Cannot update. No records found for " + enrollee.getId()); 
		  
	  }
  
  
  @Test
  public void validateInsertEnrolleeTest() {
  	Long id = 1234l;
	Enrollee enrollee = new Enrollee();
	enrollee.setId(id);
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Name cannot be empty", e.getErrMsg());
	}

	enrollee.setId(id);
	enrollee.setName("N");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Activation status cannot be empty", e.getErrMsg());
	}
	
	enrollee.setId(id);
	enrollee.setName("N");
	enrollee.setActivationStatus("true");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Birthdate cannot be empty", e.getErrMsg());
	}
	
	enrollee.setId(id);
	enrollee.setName("N");
	enrollee.setActivationStatus("true");
	enrollee.setBirthDate("09/14/2020");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Invalid date. Please follow format [dd-MM-yyyy]", e.getErrMsg());
	}
	
	enrollee.setBirthDate("09-12-2020");
	enrollee.setPhoneNumber("111111");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Phone number is not valid", e.getErrMsg());
	}
	enrollee.setPhoneNumber("111111a");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Phone number can only be numberic", e.getErrMsg());
	}
	enrollee.setPhoneNumber("9999999999");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		// Exceptions means failure
		assertTrue(1==2);
	}
	List<Dependent> dependents = new ArrayList<>();
	Dependent d = new Dependent();
	enrollee.setDependents(dependents);
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Dependent Name cannot be empty", e.getErrMsg());
	}
	d.setName("n");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Dependent BirthDate cannot be empty", e.getErrMsg());
	}
	d.setBirthDate("09/12/2020");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		assertEquals("Invalid date. Please follow format [dd-MM-yyyy]", e.getErrMsg());
	}
	d.setBirthDate("09-09-2019");
	try {
		enrolleeServiceImpl.validateInsertEnrollee(enrollee);
	} catch (ValidationException e) {
		// Exceptions means failure
		assertTrue(1==2);
	}
  }
  
    
    

}
