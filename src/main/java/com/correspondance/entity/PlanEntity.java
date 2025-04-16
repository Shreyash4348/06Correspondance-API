package com.correspondance.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PLANMASTER")
public class PlanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	private String planName;
	private String activeSw;

	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Integer planCategoryId;

	private String createdBy;
	private String updatedBy;

	@Column(name = "CREATEDATE", updatable = false)
	@CreationTimestamp
	private LocalDate createDate;

	@Column(name = "UPDATEDATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updateDate;

}
