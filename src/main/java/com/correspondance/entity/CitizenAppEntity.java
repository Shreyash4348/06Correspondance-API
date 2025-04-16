package com.correspondance.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="CITIZEN_APP")
public class CitizenAppEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appId;

	private String fullName;

	private String email;
	
	private LocalDate dob;

	private Long phoneNumber;

	private String gender;

	private Long ssn;

	private String stateName;

	@CreationTimestamp
	private LocalDate createDate;

	@UpdateTimestamp
	private LocalDate updateDate;

	private String createdBy;

	private String updatedBy;
}
