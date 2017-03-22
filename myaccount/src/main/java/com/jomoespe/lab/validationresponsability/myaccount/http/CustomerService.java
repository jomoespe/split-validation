package com.jomoespe.lab.validationresponsability.myaccount.http;

import static spark.Spark.post;

import static javax.json.Json.createReader;

import com.jomoespe.lab.validationresponsability.myaccount.core.Customer;
import com.jomoespe.lab.validationresponsability.validation.Validation;
import com.jomoespe.lab.validationresponsability.validation.Transformer;

import spark.Request;
import spark.Response;

import java.io.StringReader;
import java.util.function.Function;
import javax.json.JsonObject;

public class CustomerService {
    private final Function<Customer, Validation> validation;
    
    public CustomerService(Function<Customer, Validation> validation) {
        this.validation = validation;
        post("/customer/form/validation", this::validate, Transformer::toJson);
    }

    public Validation validate(final Request request, final Response response) {
        Customer customer = toCustomer.compose(toJson).apply(request.body());
        return validation.apply(customer);
    }

    final Function<String, JsonObject> toJson = (message) -> 
            createReader(new StringReader(message)).readObject();

    final Function<JsonObject, Customer> toCustomer = (json) -> 
        new Customer.Builder()
            .name(json.getString("name"))
            .email(json.getString("email"))
            .phone(json.getString("phone"))
            .build();
}
 