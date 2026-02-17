package com.br.preco.justo.dto;

import com.br.preco.justo.model.StatusPato;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PatoResponse {
    private Long idPato;
    private String nome;
    private Long idMae;
    private StatusPato statusPato;
}
