package com.hienhao.cleanarch.domain.usecases.shared;

import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;

public interface UseCaseOnlyOutput<O> {

    public O execute() throws DomainException;
}
