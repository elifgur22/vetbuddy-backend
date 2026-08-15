package com.vetbuddy.vetbuddy_backend.medication.dto;

import com.vetbuddy.vetbuddy_backend.medication.domain.MedicationDoseStatus;

import java.time.LocalDateTime;

public record MedicationLogResponse(
        Long id,
        Long medicationId,
        String medicationName,
        String dose,
        LocalDateTime scheduledAt,
        LocalDateTime givenAt,
        MedicationDoseStatus status
) {
}