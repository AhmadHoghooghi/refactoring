package com.rhotiz.spark;

public class InvalidNationalCodeException extends RuntimeException {
    private final String nationalCode;

    public InvalidNationalCodeException(String message, String nationalCode) {
        super(message);
        this.nationalCode = nationalCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }
}
