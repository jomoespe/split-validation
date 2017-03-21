package com.jomoespe.lab.validationresponsability.myaccount.adapters;

import static com.jomoespe.lab.validationresponsability.myaccount.Config.finaceBankAccountValidationUrl;

import static com.mashape.unirest.http.Unirest.put;

import com.jomoespe.lab.validationresponsability.myaccount.rest.CustomerForm;
import com.jomoespe.lab.validationresponsability.validation.Validation;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.function.Function;

public class FinancesValidation implements Function<CustomerForm, Validation> {
    @Override
    public Validation apply(CustomerForm customer) {
        try {
            HttpResponse<JsonNode> response = put(finaceBankAccountValidationUrl.get())
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
        String.format("{\"account\": \"%s\"}", 
                      customer.bankAccount().orElse(""));
}
