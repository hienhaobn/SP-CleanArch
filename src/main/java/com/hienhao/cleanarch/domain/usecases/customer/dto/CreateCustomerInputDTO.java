package com.hienhao.cleanarch.domain.usecases.customer.dto;

public record CreateCustomerInputDTO(
        String name,
        String email,
        String street,
        String number,
        String city,
        String zip
) {}
