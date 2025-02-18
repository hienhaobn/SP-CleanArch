package com.hienhao.cleanarch.domain.entites.customer;

import com.hienhao.cleanarch.domain.entites.shared.entities.BaseEntity;
import com.hienhao.cleanarch.domain.entites.shared.exceptions.BusinessException;

import java.util.UUID;

public interface Customer extends BaseEntity {

    public UUID getId();
    public String getName();
    public String getEmail();
    public Address getAddress();
    public Boolean isActive();

    public Customer changeName(String newName) throws BusinessException;
    public Customer changeEmail(String newEmail) throws BusinessException;
    public Customer changeAddress(Address newAddr) throws BusinessException;
    public Customer activate();
    public Customer deactivate();
}
