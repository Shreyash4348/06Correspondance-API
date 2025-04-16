package com.correspondance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correspondance.entity.PlanEntity;

public interface PlanEntityRepo extends JpaRepository<PlanEntity, Integer>{

}
