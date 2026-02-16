package com.br.preco.justo.exception;

public class CpfAlreadyRegistered extends RuntimeException {
    public CpfAlreadyRegistered(String message) {
        super(message);
    }
}
