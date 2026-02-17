package com.br.preco.justo.controller;

import com.br.preco.justo.dto.VendaDto;
import com.br.preco.justo.dto.VendaResponse;
import com.br.preco.justo.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venda")
@RequiredArgsConstructor
public class VendaController {


    private final VendaService vendaService;

    @PostMapping
    @Operation(summary = "Cadastro de vendas", description = "Cadastro de vendas",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Venda cadastrada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Id não encontrado")
            }
    )
    public ResponseEntity<VendaResponse> create(@Valid @RequestBody VendaDto dto){
        var venda = vendaService.fazerVenda(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(venda);
    }

}
