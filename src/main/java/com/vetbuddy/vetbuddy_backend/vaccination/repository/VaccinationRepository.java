package com.vetbuddy.vetbuddy_backend.vaccination.repository;

import com.vetbuddy.vetbuddy_backend.vaccination.domain.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VaccinationRepository
        extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findAllByPetIdOrderByVaccinationDateDesc(Long petId);

    Optional<Vaccination> findByIdAndPetId(Long id, Long petId);
}