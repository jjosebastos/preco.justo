package com.br.preco.justo.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDto {

    @NotBlank(message = "Nome e obrigatorio")
    private String nome;
    @NotBlank(message = "Matricula e obrigatoria")
    private String matricula;
    @NotBlank(message = "Cpf e obrigatorio")
    private String cpf;
}
