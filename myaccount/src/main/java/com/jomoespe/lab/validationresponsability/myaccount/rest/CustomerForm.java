package com.jomoespe.lab.validationresponsability.myaccount.rest;

import static java.util.Optional.ofNullable;

import java.util.Optional;

public final class CustomerForm {
    private final String name;
    private final String email;
    private final String phone;
    private final String bankAccount;
    private final String deliveryAddress;
    
    private CustomerForm(final String name, 
                         final String email, 
                         final String phone, 
                         final String bankAccount, 
                         final String deliveryAddress) {
        this.name            = name;
        this.email           = email;
        this.phone           = phone;
        this.bankAccount     = bankAccount;
        this.deliveryAddress = deliveryAddress;
    }
    
    public Optional<String> name()            { return ofNullable(name); }
    public Optional<String> email()           { return ofNullable(email); }
    public Optional<String> phone()           { return ofNullable(phone); }
    public Optional<String> bankAccount()     { return ofNullable(bankAccount); }
    public Optional<String> deliveryAddress() { return ofNullable(deliveryAddress); }
    
    public static class Builder {
        private String name;
        private String email;
        private String phone;
        private String bankAccount;
        private String deliveryAddress;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }
        
        public Builder phone(final String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder bankAccount(final String bankAccount) {
            this.bankAccount = bankAccount;
            return this;
        }
        
        public Builder deliveryAddress(final String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }
        
        public CustomerForm build() {
            return new CustomerForm(name, email, phone, bankAccount, deliveryAddress);
        }
    }
}
