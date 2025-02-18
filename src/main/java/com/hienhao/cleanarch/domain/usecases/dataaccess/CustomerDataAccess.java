package com.hienhao.cleanarch.domain.usecases.dataaccess;

import com.hienhao.cleanarch.domain.entites.customer.Customer;
import com.hienhao.cleanarch.domain.usecases.shared.exceptions.causes.DataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDataAccess {

    public void create(Customer c) throws DataAccessException;
    public Optional<Customer> read(UUID id) throws DataAccessException;
    public void update(Customer c) throws DataAccessException;

    public List<Customer> findByName(String name) throws DataAccessException;
    public Optional<Customer> findByEmail(String email) throws DataAccessException;
}
