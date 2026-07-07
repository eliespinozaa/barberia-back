package com.servicesnxs.service.administrative.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException(String mensaje) {
        super(mensaje);
    }
}