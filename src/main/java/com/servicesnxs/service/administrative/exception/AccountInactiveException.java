package com.servicesnxs.service.administrative.exception;

public class AccountInactiveException extends RuntimeException {
    public AccountInactiveException(String mensaje) {
        super(mensaje);
    }
}