package com.br.preco.justo.exception;

import lombok.Getter;

@Getter
public enum DataMessage {

    DATA_NOT_FOUND("Dados não encontrados"),
    DATA_INVALID("Dados inválidos");
    private final String message;

    DataMessage( String message){
        this.message = message;
    }
}
