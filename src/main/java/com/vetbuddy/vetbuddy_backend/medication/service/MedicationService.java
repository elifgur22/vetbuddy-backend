package com.vetbuddy.vetbuddy_backend.medication.service;

import com.vetbuddy.vetbuddy_backend.medication.dto.CreateMedicationRequest;
import com.vetbuddy.vetbuddy_backend.medication.dto.MedicationResponse;

import java.util.List;

public interface MedicationService {

    MedicationResponse create(
            Long petId,
            CreateMedicationRequest request
    );

    List<MedicationResponse> getByPetId(Long petId);
}