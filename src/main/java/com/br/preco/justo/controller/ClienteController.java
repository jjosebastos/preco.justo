package com.br.preco.justo.controller;

import com.br.preco.justo.dto.ClienteDto;
import com.br.preco.justo.dto.ClienteResponse;
import com.br.preco.justo.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de cliente", description = "Cadastro de cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "409", description = "Cpf já cadastrado")
            }
    )
    public ResponseEntity<ClienteResponse> create(@RequestBody @Valid ClienteDto dto){
        var created = this.clienteService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualização de cliente", description = "Atualização de clientes com base no ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            }
    )
    public ResponseEntity<ClienteResponse> update(@PathVariable Long id,
                                                  @RequestBody @Valid ClienteDto dto){
        var updated = this.clienteService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclusão de cliente", description = "Exclusão de clientes com base no ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            }
    )
    public void deleteById(@PathVariable Long id){
        clienteService.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca de cliente", description = "Exclusão de clientes com base no ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            }
    )
    public ResponseEntity<ClienteResponse> getById(@PathVariable Long id){
        var cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }
}

