package com.jomoespe.lab.validationresponsability.myaccount;

import java.util.function.Supplier;

public class Config {
    public static Supplier<String> customerCareContactValidation  = () -> "http://localhost:8080/customer/form/validation";
    
    public static Supplier<String> finaceBankAccountValidationUrl = () -> "http://localhost:8082/bankaccount/validation";
    
    public static Supplier<String> logisticsAddressValidationUrl  = () -> "http://localhost:8083/address/validation";
}
