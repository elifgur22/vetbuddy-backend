package com.vetbuddy.vetbuddy_backend.vaccination.service;

import com.vetbuddy.vetbuddy_backend.vaccination.dto.CreateVaccinationRequest;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.VaccinationResponse;

import java.util.List;

public interface VaccinationService {

    VaccinationResponse create(
            Long petId,
            CreateVaccinationRequest request
    );

    List<VaccinationResponse> getByPetId(Long petId);
}