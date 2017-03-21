package com.jomoespe.lab.validationresponsability.finances.rest;

import static spark.Spark.path;
import static spark.Spark.put;

import static javax.json.Json.createReader;

import com.jomoespe.lab.validationresponsability.finances.core.BankAccount;
import com.jomoespe.lab.validationresponsability.validation.Validation;
import com.jomoespe.lab.validationresponsability.validation.Transformer;

import spark.Request;
import spark.Response;

import java.io.StringReader;
import java.util.function.Function;
import javax.json.JsonObject;

public class BankAccountValidationService {
    private final Function<BankAccount, Validation> validation;
    
    public BankAccountValidationService(Function<BankAccount, Validation> validation) {
        this.validation = validation;
        path("/bankaccount", () -> {
            put("/validation", this::validate, Transformer::toJson);
        });
    }

    public Validation validate(final Request request, final Response response) {
        BankAccount customer = toCustomer.compose(toJson).apply(request.body());
        return validation.apply(customer);
    }

    final Function<String, JsonObject> toJson = (message) -> 
            createReader(new StringReader(message)).readObject();

    final Function<JsonObject, BankAccount> toCustomer = (json) -> 
        new BankAccount.Builder()
            .account(json.getString("account"))
            .build();
}
 