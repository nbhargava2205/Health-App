package com.foo.healthApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foo.healthApp.dao.dto.EnrolleeDto;

@Repository
public interface EnrolleRepo extends JpaRepository<EnrolleeDto, Long>{

	@Query(value = "SELECT COUNT(*) FROM ENROLLEE E WHERE E.ENROLLEE_ID = ?1", nativeQuery = true)
	public int checkIfExists(Long id);
}
