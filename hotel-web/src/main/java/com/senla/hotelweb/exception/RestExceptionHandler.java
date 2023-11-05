package com.senla.hotelweb.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestErrorMessage methodArgumentValidationException(MethodArgumentNotValidException exception) {
        log.error("methodArgumentValidationException:error -> {}", exception.getMessage());
        return new RestErrorMessage(
                "Validation Error",
                Objects.requireNonNull(exception.getFieldError()).getDefaultMessage()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestErrorMessage inputParameterValidationException(ConstraintViolationException exception) {
        log.error("inputParameterValidationException:error -> {}", exception.getMessage());
        return new RestErrorMessage(
                "Validation Error for input parameter",
                exception.getMessage()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestErrorMessage inputParameterNameException(IllegalArgumentException exception) {
        log.error("inputParameterNameException:error -> {}", exception.getMessage());
        return new RestErrorMessage(
                "Wrong input parameter name",
                exception.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorMessage handleOtherExceptions(Exception exception) {
        log.error("handleOtherExceptions:error -> {}", exception.getMessage());
        return new RestErrorMessage(
                "Internal Server Error Occurred",
                exception.getMessage());
    }

    @ExceptionHandler(InvalidParameterException.class)
    public RestErrorMessage handleInvalidParameterException(InvalidParameterException exception) {
        return new RestErrorMessage(
                "Invalid parameter received",
                exception.getMessage());
    }
}
