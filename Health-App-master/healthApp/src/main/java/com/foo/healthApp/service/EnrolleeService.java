package com.foo.healthApp.service;

import com.foo.healthApp.domain.Dependent;
import com.foo.healthApp.domain.Enrollee;

public interface EnrolleeService {
	Enrollee getEnrolleeById(Long id);
	Enrollee deleteEnrollee(Long id);
	Enrollee insertEnrollee(Enrollee enrollee);
	Enrollee updateEnrollee(Enrollee enrollee);
	Dependent deleteDependent(Long id);
}
