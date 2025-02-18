package com.hienhao.cleanarch.domain.entites.shared.exceptions.causes;

import com.hienhao.cleanarch.domain.entites.shared.exceptions.BusinessException;

public class RequiredFieldException extends BusinessException {
    public static final String PATTERN = "Field %s is required";

    public RequiredFieldException(String fieldName) {
        super(String.format(PATTERN, fieldName));
    }
}
