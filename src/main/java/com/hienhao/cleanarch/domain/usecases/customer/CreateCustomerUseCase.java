package com.hienhao.cleanarch.domain.usecases.customer;

import com.hienhao.cleanarch.domain.entites.customer.Address;
import com.hienhao.cleanarch.domain.entites.customer.Customer;
import com.hienhao.cleanarch.domain.entites.customer.CustomerFactory;
import com.hienhao.cleanarch.domain.entites.shared.exceptions.BusinessException;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CreateCustomerInputDTO;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerMapper;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerOutputDTO;
import com.hienhao.cleanarch.domain.usecases.dataaccess.CustomerDataAccess;
import com.hienhao.cleanarch.domain.usecases.shared.UseCase;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.causes.BusinessValidationException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CreateCustomerUseCase implements UseCase<CreateCustomerInputDTO, CustomerOutputDTO> {

    private final CustomerDataAccess customerDataAccess;
    private final CustomerFactory customerFactory;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerOutputDTO execute(CreateCustomerInputDTO inputDTO) throws DomainException {
        // validate input
        Address address;
        Customer customer;
        try {
            // validations should throw
            address = customerFactory.createAddress(inputDTO.street(), inputDTO.number(), inputDTO.zip(), inputDTO.city());
            customer = customerFactory.createCustomer(inputDTO.name(), inputDTO.email(), address);
        } catch (BusinessException e) {
            throw new BusinessValidationException(e.getMessage());
        }

        // validate unique email
        Optional<Customer> customerWithEmail = customerDataAccess.findByEmail(customer.getEmail());
        if (customerWithEmail.isPresent()) {
            throw new BusinessValidationException("There is already a customer with this email.");
        }

        // create
        customerDataAccess.create(customer);

        // convert response to output and return
        return customerMapper.outputFromEntity(customer);
    }
}
