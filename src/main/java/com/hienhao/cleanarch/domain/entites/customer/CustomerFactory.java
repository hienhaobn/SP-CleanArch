package com.hienhao.cleanarch.domain.entites.customer;

import com.hienhao.cleanarch.domain.entites.shared.exceptions.BusinessException;
import com.hienhao.cleanarch.domain.entites.shared.exceptions.causes.RequiredFieldException;

import java.util.UUID;

public class CustomerFactory {

    public Customer createCustomer(String name, String email, Address address) throws BusinessException {
        return new DefaultCustomer(UUID.randomUUID(), name, email, address);
    }

    public Customer recreateExistingCustomer(UUID id, String name, String email, Boolean active, Address address) throws BusinessException {
        if (id == null) {
            throw new RequiredFieldException("id");
        }

        Customer existingCustomer = new DefaultCustomer(id, name, email, address);

        return keepActiveValueForExistingCustomer(existingCustomer, active);
    }

    public Customer keepActiveValueForExistingCustomer(Customer existingCustomer, Boolean activeValue) {
        if (activeValue != null) {
            if (Boolean.TRUE.equals(activeValue)) {
                existingCustomer.activate();
            } else {
                return existingCustomer.deactivate();
            }
        }

        return existingCustomer;
    }

    public Address createAddress(String street, String city, String state, String zipCode) throws BusinessException {
        return new Address(street, city, state, zipCode);
    }
}
