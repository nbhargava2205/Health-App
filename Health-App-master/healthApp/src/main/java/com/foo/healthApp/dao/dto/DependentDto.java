package com.foo.healthApp.dao.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DEPENDENT")
public class DependentDto {
	@Id
	@GeneratedValue
	private Long dependentId;
	@Column
	private String name;
	@Column
	private String birthDate;
	@ManyToOne
	private EnrolleeDto parentEnrolee;
	public Long getId() {
		return dependentId;
	}
	public void setId(Long id) {
		this.dependentId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public EnrolleeDto getParentEnrolee() {
		return parentEnrolee;
	}
	public void setParentEnrolee(EnrolleeDto parentEnrolee) {
		this.parentEnrolee = parentEnrolee;
	}

}
