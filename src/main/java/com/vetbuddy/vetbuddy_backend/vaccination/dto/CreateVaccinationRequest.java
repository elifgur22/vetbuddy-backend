package com.vetbuddy.vetbuddy_backend.vaccination.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateVaccinationRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        @NotNull
        LocalDate vaccinationDate,

        LocalDate nextDoseDate,

        @Size(max = 150)
        String veterinarian,

        @Size(max = 1000)
        String notes,

        Boolean completed
) {
}