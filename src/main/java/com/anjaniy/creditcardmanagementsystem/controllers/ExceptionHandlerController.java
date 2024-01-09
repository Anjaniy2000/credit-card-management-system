package com.anjaniy.creditcardmanagementsystem.controllers;

import com.anjaniy.creditcardmanagementsystem.exceptions.InvalidArgumentException;
import com.anjaniy.creditcardmanagementsystem.models.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse invalidArgumentException(InvalidArgumentException invalidArgumentException) {
        logger.error(invalidArgumentException.getMessage());
        return new ExceptionResponse(invalidArgumentException.getMessage(), Instant.now().toString());
    }

}
