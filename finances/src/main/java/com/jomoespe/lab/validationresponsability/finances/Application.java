package com.jomoespe.lab.validationresponsability.finances;

import static spark.Spark.port;

import com.jomoespe.lab.validationresponsability.finances.core.BankAccountValidation;
import com.jomoespe.lab.validationresponsability.finances.http.BankAccountValidationService;

public class Application {
    private static final int DEFAULT_PORT = 8082;

    private Application() {
        port(DEFAULT_PORT);
        new BankAccountValidationService(new BankAccountValidation());
    }
    
    public static void main(String...args) {
        new Application();
    }
}
