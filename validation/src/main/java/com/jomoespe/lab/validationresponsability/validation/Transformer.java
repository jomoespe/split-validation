package com.jomoespe.lab.validationresponsability.validation;

public class Transformer {
    public static String toJson(final Object model) {
        Validation validation = (Validation) model;
        return String.format("{\"valid\": %s, \"score\": %s}", validation.passed(), validation.score());
    }
}
