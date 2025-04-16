package com.correspondance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="DC_INCOME")
public class DcIncomeEntity {

	@Id
	@GeneratedValue
	private Integer incomeId;
	private Long caseNum;
	private Double empIncome;
	private Double propertyIncome;
}
