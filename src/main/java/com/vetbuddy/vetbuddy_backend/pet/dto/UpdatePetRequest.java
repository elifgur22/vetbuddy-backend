package com.vetbuddy.vetbuddy_backend.pet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdatePetRequest(

        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 50)
        String type,

        @Size(max = 100)
        String breed,

        @Size(max = 20)
        String gender,

        LocalDate birthDate,

        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal weight,

        Boolean neutered,

        @Size(max = 500)
        String photoUrl
) {
}