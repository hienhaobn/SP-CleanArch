package com.hienhao.cleanarch.domain.usecases.shared.exceptions.causes;

import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;

public class DataAccessException extends DomainException {

    public DataAccessException(String message) {
        super(message);
    }
}
