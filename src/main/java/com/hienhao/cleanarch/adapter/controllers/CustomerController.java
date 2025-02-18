package com.hienhao.cleanarch.adapter.controllers;

import com.hienhao.cleanarch.domain.usecases.customer.CustomerUseCaseFactory;
import com.hienhao.cleanarch.domain.usecases.customer.dto.*;
import com.hienhao.cleanarch.domain.usecases.shared.UseCase;
import com.hienhao.cleanarch.domain.usecases.shared.UseCaseOnlyInput;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.DomainException;

import java.util.List;

public class CustomerController {

    private final UseCase<CreateCustomerInputDTO, CustomerOutputDTO> createCustomerUseCase;
    private final UseCase<UpdateCustomerInputDTO, CustomerOutputDTO> updateCustomerUseCase;
    private final UseCase<CustomerNameInputDTO, List<CustomerOutputDTO>> findCustomerByNameUseCase;
    private final UseCase<CustomerIdDTO, CustomerOutputDTO> getCustomerByIdUseCase;
    private final UseCaseOnlyInput<CustomerIdDTO> activateCustomerUseCase;
    private final UseCaseOnlyInput<CustomerIdDTO> deactivateCustomerUseCase;

    public CustomerController(CustomerUseCaseFactory customerUseCaseFactory) {
        this.createCustomerUseCase = customerUseCaseFactory.getCreateCustomerUseCase();
        this.updateCustomerUseCase = customerUseCaseFactory.getUpdateCustomerUseCase();
        this.findCustomerByNameUseCase = customerUseCaseFactory.getFindCustomerByNameUseCase();
        this.getCustomerByIdUseCase = customerUseCaseFactory.getGetCustomerByIdUseCase();
        this.activateCustomerUseCase = customerUseCaseFactory.getActivateCustomerUseCase();
        this.deactivateCustomerUseCase = customerUseCaseFactory.getDeactivateCustomerUseCase();
    }

    public CustomerOutputDTO createCustomer(CreateCustomerInputDTO inputDTO) throws DomainException {
        return createCustomerUseCase.execute(inputDTO);
    }

    public void activateCustomer(CustomerIdDTO inputDTO) throws DomainException {
        activateCustomerUseCase.execute(inputDTO);
    }

    public void deactivateCustomer(CustomerIdDTO inputDTO) throws DomainException {
        deactivateCustomerUseCase.execute(inputDTO);
    }

    public CustomerOutputDTO updateCustomer(UpdateCustomerInputDTO inputDTO) throws DomainException {
        return updateCustomerUseCase.execute(inputDTO);
    }

    public List<CustomerOutputDTO> findCustomer(CustomerNameInputDTO inputDTO) throws DomainException {
        return findCustomerByNameUseCase.execute(inputDTO);
    }

    public CustomerOutputDTO getCustomer(CustomerIdDTO inputDTO) throws DomainException {
        return getCustomerByIdUseCase.execute(inputDTO);
    }

    public CustomerOutputDTO createActiveCustomer(CreateCustomerInputDTO inputDTO) throws DomainException {
        CustomerOutputDTO customer = createCustomerUseCase.execute(inputDTO);
        activateCustomerUseCase.execute(new CustomerIdDTO(customer.id()));
        return new CustomerOutputDTO(customer.id(), customer.name(), customer.email(), true, customer.street(), customer.number(), customer.city(), customer.city());
    }
}
