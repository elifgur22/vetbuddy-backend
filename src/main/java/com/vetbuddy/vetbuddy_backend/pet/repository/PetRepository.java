package com.vetbuddy.vetbuddy_backend.pet.repository;

import com.vetbuddy.vetbuddy_backend.pet.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}