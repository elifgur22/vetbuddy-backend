package com.vetbuddy.vetbuddy_backend.pet.controller;

import com.vetbuddy.vetbuddy_backend.pet.dto.CreatePetRequest;
import com.vetbuddy.vetbuddy_backend.pet.dto.PetResponse;
import com.vetbuddy.vetbuddy_backend.pet.dto.UpdatePetRequest;
import com.vetbuddy.vetbuddy_backend.pet.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse create(
            @Valid @RequestBody CreatePetRequest request
    ) {
        return petService.create(request);
    }

    @GetMapping
    public List<PetResponse> getAll() {
        return petService.getAll();
    }

    @GetMapping("/{id}")
    public PetResponse getById(
            @PathVariable Long id
    ) {
        return petService.getById(id);
    }

    @PutMapping("/{id}")
    public PetResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePetRequest request
    ) {
        return petService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {
        petService.delete(id);
    }
}