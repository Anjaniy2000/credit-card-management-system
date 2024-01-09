package com.anjaniy.creditcardmanagementsystem.exceptions;

public abstract class CreditCardManagementSystemException extends RuntimeException {

    private final String description;

    public CreditCardManagementSystemException(String description) {
        super(description);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
