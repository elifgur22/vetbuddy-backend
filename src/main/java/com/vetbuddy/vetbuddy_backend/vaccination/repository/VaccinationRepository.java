package com.vetbuddy.vetbuddy_backend.vaccination.repository;

import com.vetbuddy.vetbuddy_backend.vaccination.domain.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationRepository
        extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findAllByPetIdOrderByVaccinationDateDesc(Long petId);
}