package com.br.preco.justo.controller;

import com.br.preco.justo.dto.PatoDto;
import com.br.preco.justo.dto.PatoResponse;
import com.br.preco.justo.service.PatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pato")
public class PatoController {

    private final PatoService patoService;

    public PatoController(PatoService patoService) {
        this.patoService = patoService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de patos", description = "Cadastro de patos",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pato cadastrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Pato não encontrado")
            }
    )
    public ResponseEntity<PatoResponse> create(@RequestBody @Valid PatoDto dto) {
        var created = this.patoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de pato", description = "Atualização de patos com base no ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pato atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
                    @ApiResponse(responseCode = "404", description = "Pato não encontrado")
            }
    )
    public ResponseEntity<PatoResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid PatoDto dto) {
        var updated = this.patoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }



    @GetMapping("/{id}")
    @Operation(summary = "Busca de patos", description = "Busca de patos com base no ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pato encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pato não encontrado")
            }
    )
    public ResponseEntity<PatoResponse> getById(@PathVariable Long id) {
        var foundPato = patoService.findById(id);
        return ResponseEntity.ok(foundPato);
    }


}
