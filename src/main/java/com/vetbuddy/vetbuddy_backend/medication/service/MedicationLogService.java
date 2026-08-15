package com.vetbuddy.vetbuddy_backend.medication.service;

import com.vetbuddy.vetbuddy_backend.medication.dto.MedicationLogResponse;

import java.time.LocalDate;
import java.util.List;

public interface MedicationLogService {

    List<MedicationLogResponse> createDailyLogs(
            Long petId,
            LocalDate date
    );

    List<MedicationLogResponse> getDailyLogs(
            Long petId,
            LocalDate date
    );

    MedicationLogResponse markGiven(Long logId);

    MedicationLogResponse markMissed(Long logId);
}