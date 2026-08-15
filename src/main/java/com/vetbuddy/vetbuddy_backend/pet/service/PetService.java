package com.vetbuddy.vetbuddy_backend.pet.service;

import com.vetbuddy.vetbuddy_backend.pet.dto.CreatePetRequest;
import com.vetbuddy.vetbuddy_backend.pet.dto.PetResponse;
import com.vetbuddy.vetbuddy_backend.pet.dto.UpdatePetRequest;

import java.util.List;

public interface PetService {

    PetResponse create(CreatePetRequest request);

    List<PetResponse> getAll();

    PetResponse getById(Long id);

    PetResponse update(Long id, UpdatePetRequest request);

    void delete(Long id);
}