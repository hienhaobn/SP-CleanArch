package com.hienhao.cleanarch.domain.usecases.customer;

import com.hienhao.cleanarch.domain.entites.customer.Customer;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerIdDTO;
import com.hienhao.cleanarch.domain.usecases.dataaccess.CustomerDataAccess;
import com.hienhao.cleanarch.domain.usecases.shared.UseCaseOnlyInput;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DeactivateCustomerUseCase implements UseCaseOnlyInput<CustomerIdDTO> {

    private final CustomerDataAccess customerDataAccess;

    @Override
    public void execute(CustomerIdDTO inputDTO) throws DomainException {
        if (inputDTO.id() == null) {
            throw new DomainException("Customer id is required.");
        }

        Optional<Customer> customer = customerDataAccess.read(inputDTO.id());

        if (!customer.isPresent()) {
            throw new DomainException("Customer not found.");
        }

        if (Boolean.TRUE.equals(customer.get().isActive())) {
            customer.get().deactivate();
            customerDataAccess.update(customer.get());
        }
    }
}
