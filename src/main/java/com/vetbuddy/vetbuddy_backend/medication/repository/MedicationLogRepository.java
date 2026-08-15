package com.vetbuddy.vetbuddy_backend.medication.repository;

import com.vetbuddy.vetbuddy_backend.medication.domain.MedicationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicationLogRepository
        extends JpaRepository<MedicationLog, Long> {

    List<MedicationLog> findAllByMedicationIdOrderByScheduledAtDesc(
            Long medicationId
    );

    List<MedicationLog> findAllByScheduledAtBetween(
            LocalDateTime from,
            LocalDateTime to
    );

    List<MedicationLog>
    findAllByMedicationPetIdAndScheduledAtBetweenOrderByScheduledAtAsc(
            Long petId,
            LocalDateTime from,
            LocalDateTime to
    );

    boolean existsByMedicationIdAndScheduledAt(
            Long medicationId,
            LocalDateTime scheduledAt
    );
}