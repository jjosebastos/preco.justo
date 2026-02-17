package com.br.preco.justo.dto;

import com.br.preco.justo.model.Cliente;
import com.br.preco.justo.model.Vendedor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VendaResponse {

    private Long idVenda;
    private LocalDateTime dataVenda;
    private BigDecimal valorVenda;
    private Cliente cliente;
    private Vendedor vendedor;
    private Boolean flDesconto;
}
