package com.vetbuddy.vetbuddy_backend.vaccination.service;

import com.vetbuddy.vetbuddy_backend.common.exception.PetNotFoundException;
import com.vetbuddy.vetbuddy_backend.common.exception.VaccinationNotFoundException;
import com.vetbuddy.vetbuddy_backend.pet.domain.Pet;
import com.vetbuddy.vetbuddy_backend.pet.repository.PetRepository;
import com.vetbuddy.vetbuddy_backend.vaccination.domain.Vaccination;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.CreateVaccinationRequest;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.UpdateVaccinationRequest;
import com.vetbuddy.vetbuddy_backend.vaccination.dto.VaccinationResponse;
import com.vetbuddy.vetbuddy_backend.vaccination.mapper.VaccinationMapper;
import com.vetbuddy.vetbuddy_backend.vaccination.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final PetRepository petRepository;
    private final VaccinationMapper vaccinationMapper;

    @Override
    public VaccinationResponse create(
            Long petId,
            CreateVaccinationRequest request
    ) {
        final Pet pet = petRepository.findById(petId)
                .orElseThrow(
                        () -> new PetNotFoundException(petId)
                );

        final Vaccination vaccination =
                vaccinationMapper.toEntity(request, pet);

        return vaccinationMapper.toResponse(
                vaccinationRepository.save(vaccination)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<VaccinationResponse> getByPetId(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new PetNotFoundException(petId);
        }

        return vaccinationRepository
                .findAllByPetIdOrderByVaccinationDateDesc(petId)
                .stream()
                .map(vaccinationMapper::toResponse)
                .toList();
    }

    @Override
    public VaccinationResponse update(
            Long petId,
            Long vaccinationId,
            UpdateVaccinationRequest request
    ) {
        final Vaccination vaccination =
                vaccinationRepository
                        .findByIdAndPetId(vaccinationId, petId)
                        .orElseThrow(
                                () -> new VaccinationNotFoundException(
                                        vaccinationId
                                )
                        );

        vaccinationMapper.updateEntity(
                vaccination,
                request
        );

        return vaccinationMapper.toResponse(
                vaccinationRepository.save(vaccination)
        );
    }

    @Override
    public void delete(
            Long petId,
            Long vaccinationId
    ) {
        final Vaccination vaccination =
                vaccinationRepository
                        .findByIdAndPetId(vaccinationId, petId)
                        .orElseThrow(
                                () -> new VaccinationNotFoundException(
                                        vaccinationId
                                )
                        );

        vaccinationRepository.delete(vaccination);
    }
}