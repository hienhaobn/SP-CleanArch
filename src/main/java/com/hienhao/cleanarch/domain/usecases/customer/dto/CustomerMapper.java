package com.hienhao.cleanarch.domain.usecases.customer.dto;

import com.hienhao.cleanarch.domain.entites.customer.Customer;

public class CustomerMapper {

    public CustomerOutputDTO outputFromEntity(Customer c) {
        return new CustomerOutputDTO(
                c.getId(),
                c.getName(),
                c.getEmail(),
                c.isActive(),
                c.getAddress().getStreet(),
                c.getAddress().getNumber(),
                c.getAddress().getCity(),
                c.getAddress().getZip()
        );
    }
}
