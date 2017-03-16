package com.jomoespe.lab.validationresponsability.myaccount.core;

import static java.util.Optional.ofNullable;

import java.util.Objects;
import java.util.Optional;

public class Customer {
    private final String name;
    private final String email;
    private final String phone;
    private final int    hashCode;
    
    private Customer(final String name, 
                     final String email,
                     final String phone) {
        this.name     = name;
        this.email    = email;
        this.phone    = phone;
        this.hashCode = calculateHashCode(); // precomputed hashcode value
    }
    
    public Optional<String> name()  { return ofNullable(name); }
    public Optional<String> email() { return ofNullable(email); }
    public Optional<String> phone() { return ofNullable(phone); }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)                         { return true; }
        if (obj == null)                         { return false; }
        if (getClass() != obj.getClass())        { return false; }
        final Customer other = (Customer) obj;
        if (!Objects.equals(name, other.name))   { return false; }
        if (!Objects.equals(email, other.email)) { return false; }
        if (!Objects.equals(phone, other.phone)) { return false; }
        return hashCode == other.hashCode;
    }
    
    private int calculateHashCode() {
        int hash = 17;
        hash = 74 * hash + Objects.hashCode(name);
        hash = 74 * hash + Objects.hashCode(email);
        hash = 74 * hash + Objects.hashCode(phone);
        return hash;
    }

    public static class Builder {
        private String name;
        private String email;
        private String phone;

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
        
        public Customer build() {
            return new Customer(name, email, phone);
        }
    }
}
