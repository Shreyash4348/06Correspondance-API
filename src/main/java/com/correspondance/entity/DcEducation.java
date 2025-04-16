package com.correspondance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="DC_EDUCATION")
public class DcEducation {
	
	@Id
	@GeneratedValue
	private Integer eduId;
	private Long caseNum;	private String highestQualification;
	private Integer graduationYear;

}
