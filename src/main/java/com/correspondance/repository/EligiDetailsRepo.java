package com.correspondance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correspondance.entity.EligibilityDEntity;

public interface EligiDetailsRepo extends JpaRepository<EligibilityDEntity, Integer>{

	public EligibilityDEntity findByCaseNumber(Long caseNumber);
}
