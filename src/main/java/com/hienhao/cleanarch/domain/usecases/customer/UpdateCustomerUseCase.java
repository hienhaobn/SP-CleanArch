package com.hienhao.cleanarch.domain.usecases.customer;

import com.hienhao.cleanarch.domain.entites.customer.Address;
import com.hienhao.cleanarch.domain.entites.customer.Customer;
import com.hienhao.cleanarch.domain.entites.customer.CustomerFactory;
import com.hienhao.cleanarch.domain.entites.shared.exceptions.BusinessException;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerMapper;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerOutputDTO;
import com.hienhao.cleanarch.domain.usecases.customer.dto.UpdateCustomerInputDTO;
import com.hienhao.cleanarch.domain.usecases.dataaccess.CustomerDataAccess;
import com.hienhao.cleanarch.domain.usecases.shared.UseCase;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.causes.BusinessValidationException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateCustomerUseCase implements UseCase<UpdateCustomerInputDTO, CustomerOutputDTO> {

    private final CustomerDataAccess customerDataAccess;
    private final CustomerFactory customerFactory;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerOutputDTO execute(UpdateCustomerInputDTO inputDTO) throws DomainException {
        // validate input
        Address address;
        Customer updatedCustomer;

        try {
            // validations should throw
            address = customerFactory.createAddress(inputDTO.street(), inputDTO.number(), inputDTO.zip(), inputDTO.city());
            updatedCustomer = customerFactory.recreateExistingCustomer(inputDTO.id(), inputDTO.name(), inputDTO.email(), null, address);
        } catch (BusinessException e) {
            throw new BusinessValidationException(e.getMessage());
        }

        // fetch from data access
        Optional<Customer> existingCustomer = customerDataAccess.read(inputDTO.id());
        if (!existingCustomer.isPresent()) {
            throw new DomainException("Customer not found.");
        }

        // verify email changed
        if (!updatedCustomer.getEmail().equals(existingCustomer.get().getEmail())) {
            // validate uniqueness
            Optional<Customer> customerWithUpdateMail = customerDataAccess.findByEmail(updatedCustomer.getEmail());

            // if there is a customer with this email already
            if (customerWithUpdateMail.isPresent()) {
                // error if not unique
                throw new BusinessValidationException("There is already a customer with this email.");
            }
        }

        // update data and save

        // keep the active status
        if (Boolean.TRUE.equals(existingCustomer.get().isActive())) {
            updatedCustomer.activate();
        } else {
            updatedCustomer.deactivate();
        }

        customerDataAccess.update(updatedCustomer);

        return customerMapper.outputFromEntity(updatedCustomer);
    }
}
