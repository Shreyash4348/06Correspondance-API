package com.correspondance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correspondance.entity.DcCaseEntity;


public interface DcCaseRepo extends JpaRepository<DcCaseEntity, Long>{

	public DcCaseEntity findByAppId(Integer appId);
}
