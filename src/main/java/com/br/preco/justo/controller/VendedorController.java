package com.br.preco.justo.controller;

import com.br.preco.justo.dto.VendedorDto;
import com.br.preco.justo.dto.VendedorResponse;
import com.br.preco.justo.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }


    @PostMapping
    @Operation(summary = "Cadastro de vendedores", description = "Cadastro de vendedores",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Vendedor cadastrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Vendedore não encontrado")
            }
    )
    public ResponseEntity<VendedorResponse> create(@RequestBody @Valid VendedorDto dto){
        var created = vendedorService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de vendedor", description = "Atualização de vendedor com base no ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Vendedor atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
                    @ApiResponse(responseCode = "404", description = "Vendedor não encontrado")
            }
    )
    public ResponseEntity<VendedorResponse> update(@PathVariable Long id,
                                                   @RequestBody @Valid VendedorDto dto){
        var updated = vendedorService.update(id, dto);
        return ResponseEntity.ok(updated);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca de vendedores", description = "Busca de vendedor com base no ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Vendedor encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Vendedor não encontrado")
            }
    )
    public ResponseEntity<VendedorResponse> getById(@PathVariable Long id){
        var found = vendedorService.findById(id);
        return ResponseEntity.ok(found);
    }

}
