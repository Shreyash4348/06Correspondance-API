package com.correspondance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="CORRESPONDANCE_TRIGGER")
public class CorrspTriggerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trgId;
	
	private Long caseNum;
	
	@Lob
	private byte[] coPdf;
	private String trgStatus;
}
