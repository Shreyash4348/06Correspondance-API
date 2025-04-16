package com.correspondance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correspondance.entity.CitizenAppEntity;

public interface CitizenAppRepo extends JpaRepository<CitizenAppEntity, Integer>{

}
