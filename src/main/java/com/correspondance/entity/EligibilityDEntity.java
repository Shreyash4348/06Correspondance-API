package com.correspondance.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="ELIGIBLITY_DETAILS")
public class EligibilityDEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edTraceId;
	private Long caseNumber;
	private String holderName;
	private Long holderSsn;
	private String planName;
	private String planStatus;
	
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benifitAmt;
	
	private String denialReason;
	
}
