package com.br.preco.justo.mock;

import com.br.preco.justo.dto.ClienteDto;
import com.br.preco.justo.model.Cliente;

import java.time.Instant;

public class ClienteMock {

    public static ClienteDto createValidDto() {
        return ClienteDto.builder()
                .nome("Joao da Silva")
                .cpf("123.456.789-00")
                .flDesconto(true)
                .build();
    }

    public static ClienteDto createUpdateDto(){
        return ClienteDto.builder()
                .nome("Joao da Silva")
                .cpf("111.222.333-66")
                .flDesconto(true)
                .build();
    }

    public static Cliente createValidEntity() {
        return Cliente.builder()
                .idCliente(1L)
                .nome("Joao da Silva")
                .cpf("111.222.333-66")
                .flDesconto(true)
                .dataCadastro(Instant.now())
                .build();
    }
}
