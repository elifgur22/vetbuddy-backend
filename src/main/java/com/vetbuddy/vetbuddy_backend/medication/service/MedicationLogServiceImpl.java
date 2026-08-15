package com.vetbuddy.vetbuddy_backend.medication.service;

import com.vetbuddy.vetbuddy_backend.common.exception.MedicationNotFoundException;
import com.vetbuddy.vetbuddy_backend.common.exception.PetNotFoundException;
import com.vetbuddy.vetbuddy_backend.medication.domain.Medication;
import com.vetbuddy.vetbuddy_backend.medication.domain.MedicationDoseStatus;
import com.vetbuddy.vetbuddy_backend.medication.domain.MedicationLog;
import com.vetbuddy.vetbuddy_backend.medication.dto.MedicationLogResponse;
import com.vetbuddy.vetbuddy_backend.medication.repository.MedicationLogRepository;
import com.vetbuddy.vetbuddy_backend.medication.repository.MedicationRepository;
import com.vetbuddy.vetbuddy_backend.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicationLogServiceImpl implements MedicationLogService {

    private final MedicationRepository medicationRepository;
    private final MedicationLogRepository medicationLogRepository;
    private final PetRepository petRepository;

    @Override
    public List<MedicationLogResponse> createDailyLogs(
            Long petId,
            LocalDate date
    ) {
        if (!petRepository.existsById(petId)) {
            throw new PetNotFoundException(petId);
        }

        final List<Medication> medications =
                medicationRepository.findAllByPetIdOrderByStartDateDesc(petId);

        final List<MedicationLog> logs = new ArrayList<>();

        for (Medication medication : medications) {

            final boolean started =
                    !date.isBefore(medication.getStartDate());

            final boolean notEnded =
                    medication.getEndDate() == null
                            || !date.isAfter(medication.getEndDate());

            if (!started || !notEnded) {
                continue;
            }

            for (String timeValue : medication.getTimes()) {
                final LocalTime time = LocalTime.parse(timeValue);

                final MedicationLog log = MedicationLog.builder()
                        .medication(medication)
                        .scheduledAt(LocalDateTime.of(date, time))
                        .status(MedicationDoseStatus.PENDING)
                        .build();

                logs.add(log);
            }
        }

        return medicationLogRepository.saveAll(logs)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicationLogResponse> getDailyLogs(
            Long petId,
            LocalDate date
    ) {
        if (!petRepository.existsById(petId)) {
            throw new PetNotFoundException(petId);
        }

        final LocalDateTime from = date.atStartOfDay();
        final LocalDateTime to = date.plusDays(1).atStartOfDay();

        return medicationLogRepository
                .findAllByMedicationPetIdAndScheduledAtBetweenOrderByScheduledAtAsc(
                        petId,
                        from,
                        to
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public MedicationLogResponse markGiven(Long logId) {
        final MedicationLog log = medicationLogRepository.findById(logId)
                .orElseThrow(() -> new MedicationNotFoundException(logId));

        log.setStatus(MedicationDoseStatus.GIVEN);
        log.setGivenAt(LocalDateTime.now());

        return toResponse(
                medicationLogRepository.save(log)
        );
    }

    @Override
    public MedicationLogResponse markMissed(Long logId) {
        final MedicationLog log = medicationLogRepository.findById(logId)
                .orElseThrow(() -> new MedicationNotFoundException(logId));

        log.setStatus(MedicationDoseStatus.MISSED);
        log.setGivenAt(null);

        return toResponse(
                medicationLogRepository.save(log)
        );
    }

    private MedicationLogResponse toResponse(MedicationLog log) {
        return new MedicationLogResponse(
                log.getId(),
                log.getMedication().getId(),
                log.getMedication().getName(),
                log.getMedication().getDose(),
                log.getScheduledAt(),
                log.getGivenAt(),
                log.getStatus()
        );
    }
}