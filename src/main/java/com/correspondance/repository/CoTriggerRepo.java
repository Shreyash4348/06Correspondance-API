package com.correspondance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.correspondance.entity.CorrspTriggerEntity;

@Repository
public interface CoTriggerRepo extends JpaRepository<CorrspTriggerEntity, Integer>{

	public List<CorrspTriggerEntity> findByTrgStatus(String status);
	
	public CorrspTriggerEntity findByCaseNum(Long caseNum);
}
