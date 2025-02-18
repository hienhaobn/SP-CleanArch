package com.hienhao.cleanarch.domain.usecases.customer.dto;

import java.util.UUID;

public record UpdateCustomerInputDTO(
        UUID id,
        String name,
        String email,
        String street,
        String number,
        String city,
        String zip
) {}
