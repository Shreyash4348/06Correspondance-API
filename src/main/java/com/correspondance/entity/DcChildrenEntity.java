package com.correspondance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "DC_CHILDREN")
public class DcChildrenEntity {

	@Id
	@GeneratedValue
	private Integer childId;
	private Integer childAge;
	private Long chilSsn;
	private Long caseNum;
}
