package com.vetbuddy.vetbuddy_backend.medication.service;

import com.vetbuddy.vetbuddy_backend.medication.dto.CreateMedicationRequest;
import com.vetbuddy.vetbuddy_backend.medication.dto.MedicationResponse;
import com.vetbuddy.vetbuddy_backend.medication.dto.UpdateMedicationRequest;

import java.util.List;

public interface MedicationService {

    MedicationResponse create(
            Long petId,
            CreateMedicationRequest request
    );

    List<MedicationResponse> getByPetId(Long petId);

    MedicationResponse update(
            Long petId,
            Long medicationId,
            UpdateMedicationRequest request
    );

    void delete(
            Long petId,
            Long medicationId
    );
}