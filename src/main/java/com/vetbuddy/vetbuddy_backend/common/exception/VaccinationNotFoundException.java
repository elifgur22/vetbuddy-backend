package com.vetbuddy.vetbuddy_backend.common.exception;

public class VaccinationNotFoundException extends RuntimeException {

    public VaccinationNotFoundException(Long id) {
        super("Vaccination not found with id: " + id);
    }
}