package com.foo.healthApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foo.healthApp.dao.dto.DependentDto;

@Repository
public interface DependentRepo extends JpaRepository<DependentDto, Long>{

	@Query(value = "SELECT COUNT(*) FROM DEPENDENT E WHERE E.DEPENDENT_ID = ?1", nativeQuery = true)
	public int checkIfExists(Long id);
}
