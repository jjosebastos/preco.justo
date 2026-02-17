package com.br.preco.justo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaDto {

    @NotNull
    private Long idCliente;

    @NotNull
    private Long idVendedor;

    @NotEmpty(message = "A venda deve ter pelo menos um pato")
    private List<Long> idsPatos;
}
