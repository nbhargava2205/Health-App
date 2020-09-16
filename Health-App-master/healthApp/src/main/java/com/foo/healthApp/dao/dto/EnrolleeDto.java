package com.foo.healthApp.dao.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.foo.healthApp.domain.Dependent;

@Entity
@Table(name = "ENROLLEE")
public class EnrolleeDto {
	@Id
	@GeneratedValue
	private Long enrolleeId;
	@Column
	private String name;
	@Column
	private String activationStatus;
	@Column
	private String birthDate;
	@Column
	private String phoneNumber;
	@OneToMany(mappedBy = "parentEnrolee")
	@Cascade(CascadeType.ALL)
	private List<DependentDto> dependents;
	public Long getId() {
		return enrolleeId;
	}
	public void setId(Long id) {
		this.enrolleeId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getEnrolleeId() {
		return enrolleeId;
	}
	public void setEnrolleeId(Long enrolleeId) {
		this.enrolleeId = enrolleeId;
	}
	public String getActivationStatus() {
		return activationStatus;
	}
	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<DependentDto> getDependents() {
		return dependents;
	}
	public void setDependents(List<DependentDto> dependents) {
		this.dependents = dependents;
	}
	

}
