package com.vetbuddy.vetbuddy_backend.pet.service;

import com.vetbuddy.vetbuddy_backend.common.exception.PetNotFoundException;
import com.vetbuddy.vetbuddy_backend.pet.domain.Pet;
import com.vetbuddy.vetbuddy_backend.pet.dto.CreatePetRequest;
import com.vetbuddy.vetbuddy_backend.pet.dto.PetResponse;
import com.vetbuddy.vetbuddy_backend.pet.dto.UpdatePetRequest;
import com.vetbuddy.vetbuddy_backend.pet.mapper.PetMapper;
import com.vetbuddy.vetbuddy_backend.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Override
    public PetResponse create(CreatePetRequest request) {
        final Pet pet = petMapper.toEntity(request);
        final Pet savedPet = petRepository.save(pet);

        return petMapper.toResponse(savedPet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetResponse> getAll() {
        return petRepository.findAll()
                .stream()
                .map(petMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PetResponse getById(Long id) {
        return petMapper.toResponse(findPet(id));
    }

    @Override
    public PetResponse update(Long id, UpdatePetRequest request) {
        final Pet pet = findPet(id);

        petMapper.updateEntity(pet, request);

        final Pet updatedPet = petRepository.save(pet);

        return petMapper.toResponse(updatedPet);
    }

    @Override
    public void delete(Long id) {
        final Pet pet = findPet(id);
        petRepository.delete(pet);
    }

    private Pet findPet(Long id) {
        return petRepository.findById(id)
                .orElseThrow(
                        () -> new PetNotFoundException(id)
                );
    }
}