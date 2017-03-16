package com.jomoespe.lab.validationresponsability.logistics.core;

import static com.jomoespe.lab.validationresponsability.validation.Validation.fail;
import static com.jomoespe.lab.validationresponsability.validation.Validation.ok;

import com.jomoespe.lab.validationresponsability.validation.Validation;

import java.util.function.Function;
import java.util.function.Predicate;

public class AddressValidation implements Function<Address, Validation> {
    private final Predicate<Address> haveAddress = (addresss) -> 
        addresss.deliveryAddress().isPresent() &&
        !addresss.deliveryAddress().get().isEmpty();
    
    private final Predicate<Address> isAddress = (address) -> 
        address.deliveryAddress().get().length() >= 5;

    private final Predicate vaidation = haveAddress.and(isAddress);

    @Override
    public Validation apply(final Address address) {
        return vaidation.test(address) ? ok() : fail();
    }
}
