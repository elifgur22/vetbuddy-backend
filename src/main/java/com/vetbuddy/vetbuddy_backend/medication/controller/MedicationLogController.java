package com.vetbuddy.vetbuddy_backend.medication.controller;

import com.vetbuddy.vetbuddy_backend.medication.dto.MedicationLogResponse;
import com.vetbuddy.vetbuddy_backend.medication.service.MedicationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MedicationLogController {

    private final MedicationLogService medicationLogService;

    @PostMapping("/pets/{petId}/medication-logs")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MedicationLogResponse> createDailyLogs(
            @PathVariable Long petId,
            @RequestParam LocalDate date
    ) {
        return medicationLogService.createDailyLogs(
                petId,
                date
        );
    }

    @GetMapping("/pets/{petId}/medication-logs")
    public List<MedicationLogResponse> getDailyLogs(
            @PathVariable Long petId,
            @RequestParam LocalDate date
    ) {
        return medicationLogService.getDailyLogs(
                petId,
                date
        );
    }

    @PatchMapping("/medication-logs/{logId}/given")
    public MedicationLogResponse markGiven(
            @PathVariable Long logId
    ) {
        return medicationLogService.markGiven(logId);
    }

    @PatchMapping("/medication-logs/{logId}/missed")
    public MedicationLogResponse markMissed(
            @PathVariable Long logId
    ) {
        return medicationLogService.markMissed(logId);
    }
}