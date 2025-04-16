package com.correspondance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correspondance.entity.DcEducation;

public interface DcEducationRepo extends JpaRepository<DcEducation, Integer>{

	public DcEducation findByCaseNum(Long caseNum);
}
