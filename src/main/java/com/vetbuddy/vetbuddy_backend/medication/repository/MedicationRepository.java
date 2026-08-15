package com.vetbuddy.vetbuddy_backend.medication.repository;

import com.vetbuddy.vetbuddy_backend.medication.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository
        extends JpaRepository<Medication, Long> {

    List<Medication> findAllByPetIdOrderByStartDateDesc(Long petId);

    Optional<Medication> findByIdAndPetId(
            Long id,
            Long petId
    );
}