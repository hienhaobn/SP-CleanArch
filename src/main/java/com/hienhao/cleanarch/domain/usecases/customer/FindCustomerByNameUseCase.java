package com.hienhao.cleanarch.domain.usecases.customer;

import com.hienhao.cleanarch.domain.entites.customer.Customer;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerMapper;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerNameInputDTO;
import com.hienhao.cleanarch.domain.usecases.customer.dto.CustomerOutputDTO;
import com.hienhao.cleanarch.domain.usecases.dataaccess.CustomerDataAccess;
import com.hienhao.cleanarch.domain.usecases.shared.UseCase;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindCustomerByNameUseCase implements UseCase<CustomerNameInputDTO, List<CustomerOutputDTO>> {

    private  final CustomerDataAccess customerDataAccess;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerOutputDTO> execute(CustomerNameInputDTO inputDTO) throws DomainException {
        // validate input
        if (inputDTO.name() == null || inputDTO.name().trim().isBlank() || inputDTO.name().length() < 3) {
            throw new DomainException("Customer Name is required (At least 3 char).");
        }

        List<Customer> listCustomers = customerDataAccess.findByName(inputDTO.name().trim());

        // convert response to output and return
        return listCustomers.stream()
                .map(customerMapper::outputFromEntity)
                .toList();
    }
}
