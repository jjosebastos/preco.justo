package com.br.preco.justo.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VendedorResponse {

    private Long idVendedor;
    private String nome;
    private String matricula;
    private String cpf;

}
