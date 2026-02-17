package com.br.preco.justo.exception;

public class VendedorNotFoundException extends RuntimeException {
    public VendedorNotFoundException(String message) {
        super(message);
    }
}
