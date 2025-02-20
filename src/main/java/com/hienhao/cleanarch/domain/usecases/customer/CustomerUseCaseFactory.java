package com.hienhao.cleanarch.domain.usecases.customer;


import com.hienhao.cleanarch.domain.entites.customer.CustomerFactory;
import com.hienhao.cleanarch.domain.usecases.customer.dto.*;
import com.hienhao.cleanarch.domain.usecases.dataaccess.CustomerDataAccess;
import com.hienhao.cleanarch.domain.usecases.shared.UseCase;
import com.hienhao.cleanarch.domain.usecases.shared.UseCaseOnlyInput;

import java.util.List;

public class CustomerUseCaseFactory {

    private final CustomerDataAccess customerDataAccess;
    private final CustomerFactory customerFactory = new CustomerFactory();
    private final CustomerMapper customerMapper = new CustomerMapper();

    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final FindCustomerByNameUseCase findCustomerByNameUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final ActivateCustomerUseCase activateCustomerUseCase;
    private final DeactivateCustomerUseCase deactivateCustomerUseCase;

    public CustomerUseCaseFactory(CustomerDataAccess customerDataAccess) {
        this.customerDataAccess = customerDataAccess;

        this.createCustomerUseCase = new CreateCustomerUseCase(this.customerDataAccess, this.customerFactory, this.customerMapper);
        this.updateCustomerUseCase = new UpdateCustomerUseCase(this.customerDataAccess, this.customerFactory, this.customerMapper);
        this.findCustomerByNameUseCase = new FindCustomerByNameUseCase(this.customerDataAccess, this.customerMapper);
        this.getCustomerByIdUseCase = new GetCustomerByIdUseCase(this.customerDataAccess, this.customerMapper);
        this.activateCustomerUseCase = new ActivateCustomerUseCase(this.customerDataAccess);
        this.deactivateCustomerUseCase = new DeactivateCustomerUseCase(this.customerDataAccess);
    }


    public UseCase<CreateCustomerInputDTO, CustomerOutputDTO> getCreateCustomerUseCase() {
        return this.createCustomerUseCase;
    }

    public UseCase<UpdateCustomerInputDTO, CustomerOutputDTO> getUpdateCustomerUseCase() {
        return this.updateCustomerUseCase;
    }

    public UseCase<CustomerNameInputDTO, List<CustomerOutputDTO>> getFindCustomerByNameUseCase() {
        return this.findCustomerByNameUseCase;
    }

    public UseCase<CustomerIdDTO, CustomerOutputDTO> getGetCustomerByIdUseCase() {
        return this.getCustomerByIdUseCase;
    }

    public UseCaseOnlyInput<CustomerIdDTO> getActivateCustomerUseCase() {
        return this.activateCustomerUseCase;
    }

    public UseCaseOnlyInput<CustomerIdDTO> getDeactivateCustomerUseCase() {
        return this.deactivateCustomerUseCase;
    }

}
