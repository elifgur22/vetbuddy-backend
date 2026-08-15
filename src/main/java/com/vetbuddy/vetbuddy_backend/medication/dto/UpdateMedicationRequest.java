package com.vetbuddy.vetbuddy_backend.medication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record UpdateMedicationRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        @NotBlank
        @Size(max = 100)
        String dose,

        @NotNull
        LocalDate startDate,

        LocalDate endDate,

        @NotEmpty
        List<String> times,

        @Size(max = 1000)
        String notes
) {
}