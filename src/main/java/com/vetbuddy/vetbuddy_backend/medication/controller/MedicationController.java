package com.vetbuddy.vetbuddy_backend.medication.controller;

import com.vetbuddy.vetbuddy_backend.medication.dto.CreateMedicationRequest;
import com.vetbuddy.vetbuddy_backend.medication.dto.MedicationResponse;
import com.vetbuddy.vetbuddy_backend.medication.dto.UpdateMedicationRequest;
import com.vetbuddy.vetbuddy_backend.medication.service.MedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets/{petId}/medications")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicationResponse create(
            @PathVariable Long petId,
            @Valid @RequestBody CreateMedicationRequest request
    ) {
        return medicationService.create(petId, request);
    }

    @GetMapping
    public List<MedicationResponse> getByPetId(
            @PathVariable Long petId
    ) {
        return medicationService.getByPetId(petId);
    }

    @PutMapping("/{medicationId}")
    public MedicationResponse update(
            @PathVariable Long petId,
            @PathVariable Long medicationId,
            @Valid @RequestBody UpdateMedicationRequest request
    ) {
        return medicationService.update(
                petId,
                medicationId,
                request
        );
    }

    @DeleteMapping("/{medicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long petId,
            @PathVariable Long medicationId
    ) {
        medicationService.delete(
                petId,
                medicationId
        );
    }

}