package com.hienhao.cleanarch.domain.usecases.shared;

import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;

public interface UseCaseOnlyInput<I> {

    public void execute(I inputDTO) throws DomainException;
}
