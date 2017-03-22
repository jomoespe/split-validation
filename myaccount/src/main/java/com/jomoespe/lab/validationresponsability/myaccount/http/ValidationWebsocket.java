package com.jomoespe.lab.validationresponsability.myaccount.http;

import static spark.Spark.webSocket;

import com.jomoespe.lab.validationresponsability.validation.Validation;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

@WebSocket
public class ValidationWebsocket {
    private static final String PATH = "/customerform/validation";

    private final Function<String, CustomerForm>     toCustomerCare;
    private final Function<CustomerForm, Validation> customerCareValidation;
    private final Function<CustomerForm, Validation> financeValidation;
    private final Function<CustomerForm, Validation> logisticsValidation;
    
    public ValidationWebsocket(final Supplier<Function<String, CustomerForm>>     toCustomerCare, 
                             final Supplier<Function<CustomerForm, Validation>> customerCareValidationSupplier,
                             final Supplier<Function<CustomerForm, Validation>> financeValidationSupplier,
                             final Supplier<Function<CustomerForm, Validation>> logisticsValidationSupplier) {
        this.toCustomerCare         = toCustomerCare.get();
        this.customerCareValidation = customerCareValidationSupplier.get();
        this.financeValidation      = financeValidationSupplier.get();
        this.logisticsValidation    = logisticsValidationSupplier.get();
        webSocket(PATH, this);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        CustomerForm customer = toCustomerCare.apply(message);
        Validation validation = compose(customer, customerCareValidation, financeValidation, logisticsValidation);
        session.getRemote().sendString( "{\"valid\":" + validation.passed() + ", \"score\": " + validation.score() + "}" );
    }
    
    private Validation compose(final CustomerForm customer, final Function<CustomerForm, Validation>...validations) {
        boolean    passed = true;
        float      score  = 0;
        Validation result;
        for (Function<CustomerForm, Validation> validation : validations) {
            result =  validation.apply(customer);
            passed &= result.passed();
            score  += result.score();
        }
        return new Validation.Builder()
                   .passed(passed)
                   .score(score/validations.length)
                   .build();
    }
}