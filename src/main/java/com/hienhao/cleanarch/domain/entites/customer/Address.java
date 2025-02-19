package com.hienhao.cleanarch.domain.entites.customer;

import com.hienhao.cleanarch.domain.entites.shared.entities.BaseValueObject;
import com.hienhao.cleanarch.domain.entites.shared.exceptions.BusinessException;
import com.hienhao.cleanarch.domain.entites.shared.exceptions.causes.RequiredFieldException;
import com.hienhao.cleanarch.domain.entites.shared.utils.UtilsFactory;
import com.hienhao.cleanarch.domain.entites.shared.utils.ValidationUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Address implements BaseValueObject {

    private final String street;
    private final String number;
    private final String zip;
    private final String city;

    private final ValidationUtils validationUtils;

    protected Address(String street, String number, String zip, String city) throws BusinessException {
        this.validationUtils = (new UtilsFactory()).getValidationUtils();

        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
        this.validate();
    }

    @Override
    public void validate() throws BusinessException {
        if(this.validationUtils.isNullOrEmpty(this.street)) throw new RequiredFieldException("street");
        if(this.validationUtils.isNullOrEmpty(this.number)) throw new RequiredFieldException("number");
        if(this.validationUtils.isNullOrEmpty(this.zip)) throw new RequiredFieldException("zip");
        if(this.validationUtils.isNullOrEmpty(this.city)) throw new RequiredFieldException("city");
    }
}
