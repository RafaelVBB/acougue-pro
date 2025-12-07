package com.rafael.acougue.exception;

import org.springframework.http.HttpStatus;

/**
 * Exceção genérica para erros da API com status customizável.
 */
public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final Object details;

    public ApiException(HttpStatus status, String message) {
        this(status, message, null);
    }

    public ApiException(HttpStatus status, String message, Object details) {
        super(message);
        this.status = status;
        this.details = details;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Object getDetails() {
        return details;
    }
}
