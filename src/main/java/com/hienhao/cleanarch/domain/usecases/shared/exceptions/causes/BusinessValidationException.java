package com.hienhao.cleanarch.domain.usecases.shared.exceptions.causes;

import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;

public class BusinessValidationException extends DomainException {

    public BusinessValidationException(String message) {
        super(message);
    }
}
