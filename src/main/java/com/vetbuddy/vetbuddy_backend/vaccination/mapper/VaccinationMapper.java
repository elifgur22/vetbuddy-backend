package com.vetbuddy.vetbuddy_backend.vaccination.mapper;

import com.vetbuddy.vetbuddy_backend.pet.domain.Pet;
import com.vetbuddy.vetbuddy_backend.vaccination.domain.Vaccination;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.CreateVaccinationRequest;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.UpdateVaccinationRequest;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.VaccinationResponse;
import org.springframework.stereotype.Component;

@Component
public class VaccinationMapper {

    public Vaccination toEntity(
            CreateVaccinationRequest request,
            Pet pet
    ) {
        return Vaccination.builder()
                .pet(pet)
                .name(request.name())
                .vaccinationDate(request.vaccinationDate())
                .nextDoseDate(request.nextDoseDate())
                .veterinarian(request.veterinarian())
                .notes(request.notes())
                .completed(request.completed())
                .build();
    }

    public VaccinationResponse toResponse(
            Vaccination vaccination
    ) {
        return new VaccinationResponse(
                vaccination.getId(),
                vaccination.getPet().getId(),
                vaccination.getName(),
                vaccination.getVaccinationDate(),
                vaccination.getNextDoseDate(),
                vaccination.getVeterinarian(),
                vaccination.getNotes(),
                vaccination.getCompleted(),
                vaccination.getCreatedAt(),
                vaccination.getUpdatedAt()
        );
    }

    public void updateEntity(
            Vaccination vaccination,
            UpdateVaccinationRequest request
    ) {
        vaccination.setName(request.name());
        vaccination.setVaccinationDate(request.vaccinationDate());
        vaccination.setNextDoseDate(request.nextDoseDate());
        vaccination.setVeterinarian(request.veterinarian());
        vaccination.setNotes(request.notes());
        vaccination.setCompleted(request.completed());
    }
}