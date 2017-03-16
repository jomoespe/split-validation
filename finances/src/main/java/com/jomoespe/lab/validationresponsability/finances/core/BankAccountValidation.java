package com.jomoespe.lab.validationresponsability.finances.core;

import static com.jomoespe.lab.validationresponsability.validation.Validation.ok;
import static com.jomoespe.lab.validationresponsability.validation.Validation.fail;

import static java.util.regex.Pattern.compile;

import com.jomoespe.lab.validationresponsability.validation.Validation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class BankAccountValidation implements Function<BankAccount, Validation> {
    private static final String IBAN_REGEXPR = "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}";
            
    private final Pattern isIbanRegExpr = compile(IBAN_REGEXPR);
    
    private final Predicate<BankAccount> haveAccount = (bankAccount) -> 
        bankAccount.account().isPresent() &&
        !bankAccount.account().get().isEmpty();

    private final Predicate<BankAccount> isIban = (bankAccount) -> 
        bankAccount.account().isPresent()
        ? isIbanRegExpr.matcher(bankAccount.account().get()).matches()
        : false;
    
    private final Predicate vaidation = haveAccount.and(isIban);
    
    @Override
    public Validation apply(final BankAccount bankAccount) {
        return vaidation.test( bankAccount ) ? ok() : fail();
    }
}
