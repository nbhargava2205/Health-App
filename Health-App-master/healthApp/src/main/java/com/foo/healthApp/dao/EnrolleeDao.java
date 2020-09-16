package com.foo.healthApp.dao;

import com.foo.healthApp.domain.Enrollee;
import com.foo.healthApp.exception.BeException;

public interface EnrolleeDao {
	Enrollee getEnrolleeById(Long id) throws BeException;
	void deleteEnrollee(Long id) throws BeException;
	void deleteDependent(Long id) throws BeException;
	Long insertEnrollee(Enrollee enrollee) throws BeException;
	Enrollee updateEnrollee(Enrollee enrollee) throws BeException;

}
