package com.jomoespe.lab.validationresponsability.logistics;

import static spark.Spark.port;

import com.jomoespe.lab.validationresponsability.logistics.core.AddressValidation;
import com.jomoespe.lab.validationresponsability.logistics.http.AddressValidationService;

public class Application {
    private static final int DEFAULT_PORT = 8083;

    private Application() {
        port(DEFAULT_PORT);
        new AddressValidationService(new AddressValidation());
    }
    
    public static void main(String...args) {
        new Application();
    }
}
