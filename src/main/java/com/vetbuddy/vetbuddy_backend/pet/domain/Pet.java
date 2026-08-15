package com.vetbuddy.vetbuddy_backend.pet.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pet")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(length = 100)
    private String breed;

    @Column(length = 20)
    private String gender;

    private LocalDate birthDate;

    @Column(precision = 6, scale = 2)
    private BigDecimal weight;

    @Column(nullable = false)
    private Boolean neutered;

    @Column(length = 500)
    private String photoUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        final LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;

        if (neutered == null) {
            neutered = false;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}