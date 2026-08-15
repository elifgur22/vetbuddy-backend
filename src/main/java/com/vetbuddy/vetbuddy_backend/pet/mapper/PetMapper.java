package com.vetbuddy.vetbuddy_backend.pet.mapper;

import com.vetbuddy.vetbuddy_backend.pet.domain.Pet;
import com.vetbuddy.vetbuddy_backend.pet.dto.CreatePetRequest;
import com.vetbuddy.vetbuddy_backend.pet.dto.PetResponse;
import com.vetbuddy.vetbuddy_backend.pet.dto.UpdatePetRequest;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    public Pet toEntity(CreatePetRequest request) {
        return Pet.builder()
                .name(request.name())
                .type(request.type())
                .breed(request.breed())
                .gender(request.gender())
                .birthDate(request.birthDate())
                .weight(request.weight())
                .neutered(request.neutered())
                .photoUrl(request.photoUrl())
                .build();
    }

    public void updateEntity(Pet pet, UpdatePetRequest request) {
        pet.setName(request.name());
        pet.setType(request.type());
        pet.setBreed(request.breed());
        pet.setGender(request.gender());
        pet.setBirthDate(request.birthDate());
        pet.setWeight(request.weight());
        pet.setNeutered(request.neutered());
        pet.setPhotoUrl(request.photoUrl());
    }

    public PetResponse toResponse(Pet pet) {
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getType(),
                pet.getBreed(),
                pet.getGender(),
                pet.getBirthDate(),
                pet.getWeight(),
                pet.getNeutered(),
                pet.getPhotoUrl(),
                pet.getCreatedAt(),
                pet.getUpdatedAt()
        );
    }
}