package com.br.preco.justo.service;

import com.br.preco.justo.dto.ClienteDto;
import com.br.preco.justo.dto.ClienteResponse;
import com.br.preco.justo.exception.ClienteNotFoundException;
import com.br.preco.justo.exception.CpfAlreadyRegistered;
import com.br.preco.justo.model.Cliente;
import com.br.preco.justo.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Transactional
    public ClienteResponse save(ClienteDto dto){
        if(this.clienteRepository.existsByCpfNativo(dto.getCpf())){
            throw new CpfAlreadyRegistered("CPF ja existente!");
        }
        var cliente = this.dtoToCliente(dto);
        var saved = clienteRepository.save(cliente);

        return this.entityToResponse(saved);
    }

    @Transactional
    public ClienteResponse update(Long idCliente, ClienteDto dto){
        var cliente = getCliente(idCliente);
        var updatedCliente = dtoToUpdate(dto, cliente);
        return entityToResponse(clienteRepository.save(updatedCliente));
    }

    @Transactional
    public void delete(Long idCliente){
        var cliente = getCliente(idCliente);
        clienteRepository.delete(cliente);
    }


    public ClienteResponse findById(Long id){
        return this.entityToResponse(getCliente(id));
    }


    private boolean existsByCpf(String cpf){
        return clienteRepository.existsByCpfNativo(cpf);
    }

    private Cliente getCliente(Long idCliente){
        return clienteRepository.findById(idCliente).orElseThrow
                (() -> new ClienteNotFoundException(""));
    }

    private Cliente dtoToUpdate(ClienteDto dto, Cliente entity){
        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setFlDesconto(dto.getFlDesconto());
        return entity;
    }

    private ClienteResponse entityToResponse(Cliente entity){
        return ClienteResponse.builder()
                .id(entity.getIdCliente())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .build();
    }

    private Cliente dtoToCliente(ClienteDto input){
        return Cliente.builder()
                .nome(input.getNome())
                .cpf(input.getCpf())
                .flDesconto(input.getFlDesconto())
                .dataCadastro(Instant.now())
                .build();

    }

}
