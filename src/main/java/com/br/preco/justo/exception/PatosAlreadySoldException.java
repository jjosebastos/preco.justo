package com.br.preco.justo.exception;

public class PatosAlreadySoldException extends RuntimeException {
    public PatosAlreadySoldException(String message) {
        super(message);
    }
}
