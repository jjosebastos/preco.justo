package com.br.preco.justo.dto;

import com.br.preco.justo.model.StatusPato;
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
public class PatoDto {
    @NotBlank(message = "O campo nome e obrigatorio")
    private String nome;
    private Long idMae;
    @NotNull(message = "O campo de status e obrigatorio")
    private StatusPato statusPato;

}
