package com.jomoespe.lab.validationresponsability.myaccount.adapters;

import static com.jomoespe.lab.validationresponsability.myaccount.Config.customerCareContactValidation;

import static com.mashape.unirest.http.Unirest.post;

import com.jomoespe.lab.validationresponsability.myaccount.rest.CustomerForm;
import com.jomoespe.lab.validationresponsability.validation.Validation;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.function.Function;

public class CustomerCareValidation implements Function<CustomerForm, Validation> {
    @Override
    public Validation apply(CustomerForm customer) {
        try {
            HttpResponse<JsonNode> response = post(customerCareContactValidation.get())
                                              .body(message.apply(customer))
                                              .asJson();
            return new Validation.Builder()
                       .passed(response.getBody().getObject().getBoolean("valid"))
                       .score((float)response.getBody().getObject().getDouble("score"))
                       .build();
        } catch(UnirestException e) {
            e.printStackTrace(System.err);
            return Validation.fail();
        }
    }

    private Function<CustomerForm, String> message = (customer) ->
        String.format("{\"name\":\"%s\",\"email\":\"%s\",\"phone\":\"%s\"}", 
                      customer.name().orElse(""),
                      customer.email().orElse(""),
                      customer.phone().orElse(""));
}
