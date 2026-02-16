package com.br.preco.justo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteResponse {

    private Long id;
    private String nome;
    private String cpf;
}
