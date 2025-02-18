package com.hienhao.cleanarch.domain.usecases.shared;

import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;

public interface UseCase<I, O> {

    public O execute(I inputDTO) throws DomainException;
}
