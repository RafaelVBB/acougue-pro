package com.rafael.acougue.exception;

/**
 * Exceção genérica para violações de regras de negócio.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
