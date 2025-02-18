package com.hienhao.cleanarch.domain.entites.shared.utils;

public class UtilsFactory {
    private final ValidationUtils validationUtils = new ValidationUtils();

    public ValidationUtils getValidationUtils() {
        return validationUtils;
    }
}
