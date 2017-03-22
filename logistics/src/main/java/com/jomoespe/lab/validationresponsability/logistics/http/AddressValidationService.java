package com.jomoespe.lab.validationresponsability.logistics.http;

import static spark.Spark.path;
import static spark.Spark.post;

import static javax.json.Json.createReader;

import com.jomoespe.lab.validationresponsability.logistics.core.Address;
import com.jomoespe.lab.validationresponsability.validation.Validation;
import com.jomoespe.lab.validationresponsability.validation.Transformer;

import spark.Request;
import spark.Response;

import java.io.StringReader;
import java.util.function.Function;
import javax.json.JsonObject;

public class AddressValidationService {
    private final Function<Address, Validation> validation;
    
    public AddressValidationService(Function<Address, Validation> validation) {
        this.validation = validation;
        path("/address", () -> {
            post("/validation", this::validate, Transformer::toJson);
        });
    }

    public Validation validate(final Request request, final Response response) {
        Address customer = toCustomer.compose(toJson).apply(request.body());
        return validation.apply(customer);
    }

    final Function<String, JsonObject> toJson = (message) -> 
            createReader(new StringReader(message)).readObject();

    final Function<JsonObject, Address> toCustomer = (json) -> 
        new Address.Builder()
            .deliveryAddress(json.getString("address"))
            .build();
}
 