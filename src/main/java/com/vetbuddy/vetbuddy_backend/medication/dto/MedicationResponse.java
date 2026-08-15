package com.vetbuddy.vetbuddy_backend.medication.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MedicationResponse(
        Long id,
        Long petId,
        String name,
        String dose,
        LocalDate startDate,
        LocalDate endDate,
        List<String> times,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}