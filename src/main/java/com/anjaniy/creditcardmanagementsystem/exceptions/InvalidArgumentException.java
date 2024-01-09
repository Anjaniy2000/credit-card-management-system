package com.anjaniy.creditcardmanagementsystem.exceptions;

import java.util.Map;
import java.util.function.Supplier;

public class InvalidArgumentException extends CreditCardManagementSystemException {

    private final Map<String, String> fieldToDescription;

    public InvalidArgumentException(Map<String, String> fieldToDescription) {
        super(formatExceptionMessage(fieldToDescription));
        this.fieldToDescription = fieldToDescription;
    }

    @Override
    public String getMessage() {
        return fieldToDescription.toString();
    }

    public static InvalidArgumentException from(String field, String description) {
        return new InvalidArgumentException(Map.of(field, description));
    }

    public static Supplier<InvalidArgumentException> invalidArgumentException(String field, String description) {
        return () -> InvalidArgumentException.from(field, description);
    }

    private static String formatExceptionMessage(Map<String, String> fieldToDescription) {
        return "Invalid argument(" + fieldToDescription + ") provided";
    }

}
