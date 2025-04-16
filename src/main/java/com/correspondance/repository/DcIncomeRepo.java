package com.correspondance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correspondance.entity.DcIncomeEntity;


public interface DcIncomeRepo extends JpaRepository<DcIncomeEntity, Integer>{

	public DcIncomeEntity findByCaseNum(Long caseNum);
}
