package com.vetbuddy.vetbuddy_backend.medication.repository;

import com.vetbuddy.vetbuddy_backend.medication.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository
        extends JpaRepository<Medication, Long> {

    List<Medication> findAllByPetIdOrderByStartDateDesc(Long petId);
}