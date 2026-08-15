package com.vetbuddy.vetbuddy_backend.medication.service;

import com.vetbuddy.vetbuddy_backend.common.exception.MedicationNotFoundException;
import com.vetbuddy.vetbuddy_backend.common.exception.PetNotFoundException;
import com.vetbuddy.vetbuddy_backend.medication.domain.Medication;
import com.vetbuddy.vetbuddy_backend.medication.dto.CreateMedicationRequest;
import com.vetbuddy.vetbuddy_backend.medication.dto.MedicationResponse;
import com.vetbuddy.vetbuddy_backend.medication.dto.UpdateMedicationRequest;
import com.vetbuddy.vetbuddy_backend.medication.mapper.MedicationMapper;
import com.vetbuddy.vetbuddy_backend.medication.repository.MedicationRepository;
import com.vetbuddy.vetbuddy_backend.pet.domain.Pet;
import com.vetbuddy.vetbuddy_backend.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final PetRepository petRepository;
    private final MedicationMapper medicationMapper;

    @Override
    public MedicationResponse create(
            Long petId,
            CreateMedicationRequest request
    ) {
        final Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        final Medication medication =
                medicationMapper.toEntity(request, pet);

        return medicationMapper.toResponse(
                medicationRepository.save(medication)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicationResponse> getByPetId(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new PetNotFoundException(petId);
        }

        return medicationRepository
                .findAllByPetIdOrderByStartDateDesc(petId)
                .stream()
                .map(medicationMapper::toResponse)
                .toList();
    }

    @Override
    public MedicationResponse update(
            Long petId,
            Long medicationId,
            UpdateMedicationRequest request
    ) {
        final Medication medication =
                medicationRepository
                        .findByIdAndPetId(medicationId, petId)
                        .orElseThrow(
                                () -> new MedicationNotFoundException(
                                        medicationId
                                )
                        );

        medicationMapper.updateEntity(
                medication,
                request
        );

        return medicationMapper.toResponse(
                medicationRepository.save(medication)
        );
    }

    @Override
    public void delete(
            Long petId,
            Long medicationId
    ) {
        final Medication medication =
                medicationRepository
                        .findByIdAndPetId(medicationId, petId)
                        .orElseThrow(
                                () -> new MedicationNotFoundException(
                                        medicationId
                                )
                        );

        medicationRepository.delete(medication);
    }
}