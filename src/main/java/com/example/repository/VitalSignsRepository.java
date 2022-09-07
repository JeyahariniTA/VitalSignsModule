package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.VitalSigns;

@Repository
public interface VitalSignsRepository extends JpaRepository<VitalSigns, Integer> {

}
