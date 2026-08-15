package com.vetbuddy.vetbuddy_backend.pet.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PetResponse(
        Long id,
        String name,
        String type,
        String breed,
        String gender,
        LocalDate birthDate,
        BigDecimal weight,
        Boolean neutered,
        String photoUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}