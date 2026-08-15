package com.vetbuddy.vetbuddy_backend.vaccination.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record VaccinationResponse(
        Long id,
        Long petId,
        String name,
        LocalDate vaccinationDate,
        LocalDate nextDoseDate,
        String veterinarian,
        String notes,
        Boolean completed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}