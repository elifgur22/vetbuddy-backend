package com.vetbuddy.vetbuddy_backend.vaccination.controller;

import com.vetbuddy.vetbuddy_backend.vaccination.dto.CreateVaccinationRequest;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.UpdateVaccinationRequest;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.VaccinationResponse;
import com.vetbuddy.vetbuddy_backend.vaccination.service.VaccinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets/{petId}/vaccinations")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VaccinationResponse create(
            @PathVariable Long petId,
            @Valid @RequestBody CreateVaccinationRequest request
    ) {
        return vaccinationService.create(petId, request);
    }

    @GetMapping
    public List<VaccinationResponse> getByPetId(
            @PathVariable Long petId
    ) {
        return vaccinationService.getByPetId(petId);
    }

    @PutMapping("/{vaccinationId}")
    public VaccinationResponse update(
            @PathVariable Long petId,
            @PathVariable Long vaccinationId,
            @Valid @RequestBody UpdateVaccinationRequest request
    ) {
        return vaccinationService.update(
                petId,
                vaccinationId,
                request
        );
    }

    @DeleteMapping("/{vaccinationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long petId,
            @PathVariable Long vaccinationId
    ) {
        vaccinationService.delete(
                petId,
                vaccinationId
        );
    }
}