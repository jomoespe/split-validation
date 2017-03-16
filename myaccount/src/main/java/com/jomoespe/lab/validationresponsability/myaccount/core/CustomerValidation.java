package com.jomoespe.lab.validationresponsability.myaccount.core;

import com.jomoespe.lab.validationresponsability.validation.Validation;

import java.util.function.Function;
import java.util.function.Predicate;

public class CustomerValidation implements Function<Customer, Validation>{
    private final Predicate<Customer> hasName = (customer) -> 
        customer.name().isPresent() && 
        !customer.name().get().isEmpty();

    private final Predicate<Customer> hasEmail = (customer) -> 
        customer.email().isPresent() && 
        !customer.email().get().isEmpty();
    
    private final Predicate<Customer> hasPhone = (customer) -> 
        customer.phone().isPresent() && 
        !customer.phone().get().isEmpty();

    @Override
    public Validation apply(final Customer customer) {
        boolean passed = true;
        float   score  = 0f;
        passed &= hasName.test(customer);
        score += passed ? (float) 1/3 : 0;
        passed &= hasEmail.test(customer);
        score += passed ? (float) 1/3 : 0;
        passed &= hasPhone.test(customer);
        score += passed ? (float) 1/3 : 0;
        return new Validation.Builder()
                   .passed(passed)
                   .score(score)
                   .build();
    }
}
