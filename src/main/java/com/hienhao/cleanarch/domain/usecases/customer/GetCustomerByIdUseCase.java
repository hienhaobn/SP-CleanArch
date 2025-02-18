package com.hienhao.cleanarch.domain.usecases.customer;

import com.hienhao.cleanarch.domain.entites.customer.Customer;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerIdDTO;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerMapper;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerOutputDTO;
import com.hienhao.cleanarch.domain.usecases.dataaccess.CustomerDataAccess;
import com.hienhao.cleanarch.domain.usecases.shared.UseCase;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetCustomerByIdUseCase implements UseCase<CustomerIdDTO, CustomerOutputDTO> {

    private final CustomerDataAccess customerDataAccess;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerOutputDTO execute(CustomerIdDTO inputDTO) throws DomainException {
        // validate input
        if (inputDTO.id() == null) {
            throw new DomainException("Customer id is required.");
        }

        Optional<Customer> customer = customerDataAccess.read(inputDTO.id());

        if (customer.isPresent()) {
            return customerMapper.outputFromEntity(customer.get());
        } else {
            throw new DomainException("Customer not found.");
        }
    }
}
