package com.br.preco.justo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    @NotBlank(message = "Nome nao pode ser vazio")
    private String nome;
    @NotBlank(message = "CPF nao pode ser vazio")
    private String cpf;
    @NotNull(message = "Flag de indicacao de desconto nao pode ser nulo")
    private Boolean flDesconto;


}
