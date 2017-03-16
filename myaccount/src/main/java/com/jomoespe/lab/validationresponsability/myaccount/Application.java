package com.jomoespe.lab.validationresponsability.myaccount;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.init;

import static javax.json.Json.createReader;

import com.jomoespe.lab.validationresponsability.myaccount.rest.CustomerForm;
import com.jomoespe.lab.validationresponsability.myaccount.rest.ValidationService;
import com.jomoespe.lab.validationresponsability.myaccount.adapters.CustomerCareValidation;
import com.jomoespe.lab.validationresponsability.myaccount.adapters.FinancesValidation;
import com.jomoespe.lab.validationresponsability.myaccount.adapters.LogisticsValidation;
import com.jomoespe.lab.validationresponsability.myaccount.core.CustomerValidation;
import com.jomoespe.lab.validationresponsability.myaccount.rest.CustomerService;
import com.jomoespe.lab.validationresponsability.validation.Validation;

import java.io.StringReader;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.json.JsonObject;

public class Application {
    private static final int    DEFAULT_PORT          = 8080;
    private static final String STATIC_FILES_LOCATION = "/public";

    private final Function<String, JsonObject> toJson = (message) -> 
        createReader(new StringReader(message)).readObject();

    private final Function<JsonObject, CustomerForm> toCustomerForm = (json) -> 
        new CustomerForm.Builder()
            .name(json.getString("name"))
            .email(json.getString("email"))
            .phone(json.getString("phone"))
            .bankAccount(json.getString("bankAccount"))
            .deliveryAddress(json.getString("deliveryAddress"))
            .build();
    
    private Application(final Supplier<Function<CustomerForm, Validation>> customerCareValidation,
                        final Supplier<Function<CustomerForm, Validation>> financeValidation,
                        final Supplier<Function<CustomerForm, Validation>> logisticsValidation) {
        port(DEFAULT_PORT);
        staticFiles.location(STATIC_FILES_LOCATION);
        new ValidationService(() -> toCustomerForm.compose(toJson), 
                              customerCareValidation, 
                              financeValidation, 
                              logisticsValidation);
        new CustomerService(new CustomerValidation());
        init();
    }
    
    public static void main(String...args) {
        new Application(() -> new CustomerCareValidation(), 
                        () -> new FinancesValidation(), 
                        () -> new LogisticsValidation());
    }
}
