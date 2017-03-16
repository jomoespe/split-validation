package com.jomoespe.lab.validationresponsability.logistics.core;

import static java.util.Optional.ofNullable;

import java.util.Objects;
import java.util.Optional;

public class Address {
    private final String deliveryAddress;
    private final int    hashCode;
    
    private Address(final String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        this.hashCode = calculateHashCode(); // precomputed hashcode value
    }
    
    public Optional<String> deliveryAddress() { return ofNullable(deliveryAddress); }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)                                             { return true; }
        if (obj == null)                                             { return false; }
        if (getClass() != obj.getClass())                            { return false; }
        final Address other = (Address) obj;
        if (!Objects.equals(deliveryAddress, other.deliveryAddress)) { return false; }
        return hashCode == other.hashCode;
    }
    
    private int calculateHashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(deliveryAddress);
        return hash;
    }

    public static class Builder {
        private String deliveryAddress;

        public Builder deliveryAddress(final String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }
        
        public Address build() {
            return new Address(deliveryAddress);
        }
    }
}
