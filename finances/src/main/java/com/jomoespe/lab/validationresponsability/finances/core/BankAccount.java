package com.jomoespe.lab.validationresponsability.finances.core;

import static java.util.Optional.ofNullable;

import java.util.Objects;
import java.util.Optional;

public class BankAccount {
    private final String account;
    private final int    hashCode;
    
    private BankAccount(final String account) {
        this.account = account;
        this.hashCode = calculateHashCode(); // pre-computed hashcode value
    }
    
    public Optional<String> account() { return ofNullable(account); }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)                             { return true; }
        if (obj == null)                             { return false; }
        if (getClass() != obj.getClass())            { return false; }
        final BankAccount other = (BankAccount) obj;
        if (!Objects.equals(account, other.account)) { return false; }
        return hashCode == other.hashCode;
    }
    
    private int calculateHashCode() {
        int hash = 17;
        hash = 74 * hash + Objects.hashCode(account);
        return hash;
    }

    public static class Builder {
        private String account;

        public Builder account(final String account) {
            this.account = account;
            return this;
        }
        
        public BankAccount build() {
            return new BankAccount(account);
        }
    }
}
