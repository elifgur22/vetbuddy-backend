package com.vetbuddy.vetbuddy_backend.medication.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medication_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(name = "given_at")
    private LocalDateTime givenAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MedicationDoseStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = MedicationDoseStatus.PENDING;
        }
    }
}