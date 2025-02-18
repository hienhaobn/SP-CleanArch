package com.hienhao.cleanarch.domain.entites.shared.entities;

import com.hienhao.cleanarch.domain.entites.shared.exceptions.BusinessException;

public interface BaseEntity {
    public void validate() throws BusinessException;
}
