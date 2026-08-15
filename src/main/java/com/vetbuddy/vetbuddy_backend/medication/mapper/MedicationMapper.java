package com.vetbuddy.vetbuddy_backend.medication.mapper;

import com.vetbuddy.vetbuddy_backend.medication.domain.Medication;
import com.vetbuddy.vetbuddy_backend.medication.dto.CreateMedicationRequest;
import com.vetbuddy.vetbuddy_backend.medication.dto.MedicationResponse;
import com.vetbuddy.vetbuddy_backend.medication.dto.UpdateMedicationRequest;
import com.vetbuddy.vetbuddy_backend.pet.domain.Pet;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {

    public Medication toEntity(
            CreateMedicationRequest request,
            Pet pet
    ) {
        return Medication.builder()
                .pet(pet)
                .name(request.name())
                .dose(request.dose())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .times(request.times())
                .notes(request.notes())
                .build();
    }

    public MedicationResponse toResponse(
            Medication medication
    ) {
        return new MedicationResponse(
                medication.getId(),
                medication.getPet().getId(),
                medication.getName(),
                medication.getDose(),
                medication.getStartDate(),
                medication.getEndDate(),
                medication.getTimes(),
                medication.getNotes(),
                medication.getCreatedAt(),
                medication.getUpdatedAt()
        );
    }
    public void updateEntity(
            Medication medication,
            UpdateMedicationRequest request
    ) {
        medication.setName(request.name());
        medication.setDose(request.dose());
        medication.setStartDate(request.startDate());
        medication.setEndDate(request.endDate());
        medication.setTimes(request.times());
        medication.setNotes(request.notes());
    }
}