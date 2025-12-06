package com.rafael.acougue.exception;

/**
 * Exceção padrão para recursos não encontrados.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
