package com.br.preco.justo.service;

import com.br.preco.justo.dto.VendedorDto;
import com.br.preco.justo.dto.VendedorResponse;
import com.br.preco.justo.exception.CpfAlreadyRegistered;
import com.br.preco.justo.exception.MatriculaAlreadyRegistered;
import com.br.preco.justo.exception.VendedorNotFoundException;
import com.br.preco.justo.model.Vendedor;
import com.br.preco.justo.repository.VendedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Transactional
    public VendedorResponse save(VendedorDto dto){
        if(this.vendedorRepository.existsCpf(dto.getCpf())){
            throw new CpfAlreadyRegistered("");
        }
        if(this.vendedorRepository.existsMatricula(dto.getMatricula())){
            throw new MatriculaAlreadyRegistered("");
        }
        var vendedorMap = this.dtoToVendedor(dto);
        var saved = vendedorRepository.save(vendedorMap);
        return this.entityToResponse(saved);
    }


    @Transactional
    public VendedorResponse update(Long id, VendedorDto dto){
        var found = getVendedor(id);
        var updated = dtoToUpdate(dto, found);
        return this.entityToResponse(vendedorRepository.save(updated));
    }

    public VendedorResponse findById(Long id){
        return this.entityToResponse(getVendedor(id));
    }


    private Vendedor getVendedor(Long id){
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new VendedorNotFoundException(""));
    }


    private Vendedor dtoToUpdate(VendedorDto dto, Vendedor entity){
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setMatricula(dto.getMatricula());
        return entity;
    }

    private VendedorResponse entityToResponse(Vendedor entity){
        return VendedorResponse.builder()
                .idVendedor(entity.getIdVendedor())
                .nome(entity.getNome())
                .matricula(entity.getMatricula())
                .cpf(entity.getCpf())
                .build();
    }

    private Vendedor dtoToVendedor(VendedorDto dto){
        return Vendedor.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .matricula(dto.getMatricula())
                .build();
    }


}
