package com.br.preco.justo.service;


import com.br.preco.justo.dto.PatoDto;
import com.br.preco.justo.dto.PatoResponse;
import com.br.preco.justo.exception.PatoNotFoundException;
import com.br.preco.justo.model.Pato;
import com.br.preco.justo.repository.PatoRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class PatoService {

    private final PatoRepository patoRepository;

    public PatoService(PatoRepository patoRepository) {
        this.patoRepository = patoRepository;
    }

    @Transactional
    public PatoResponse save(PatoDto dto){
        var pato = this.dtoToPato(dto);
        var saved = patoRepository.save(pato);
        return this.entityToResponse(saved);
    }


    @Transactional
    public PatoResponse update(Long id, PatoDto dto){
        var foundPato = patoRepository.findById(id)
                .orElseThrow(() ->  new PatoNotFoundException(""));
        var updatedPato = this.dtoToUpdate(dto, foundPato);
        return entityToResponse(patoRepository.save(updatedPato));
    }


    public PatoResponse findById(Long id){
        return this.entityToResponse(getPato(id));
    }

    private Pato dtoToPato(PatoDto dto) {
        return Pato.builder()
                .nome(dto.getNome())
                .status(dto.getStatusPato())
                .mae(resolveMaeReference(dto.getIdMae()))
                .build();
    }

    private Pato getPato(Long id){
        return patoRepository.findById(id)
                .orElseThrow(() -> new PatoNotFoundException(""));
    }

    private Pato resolveMaeReference(Long idMae) {
        if (idMae == null) return null;
        return patoRepository.getReferenceById(idMae);
    }

    private Pato dtoToUpdate(PatoDto dto, Pato entity){
        var mae = patoRepository.findById(dto.getIdMae())
                .orElseThrow(() -> new PatoNotFoundException(""));
        entity.setNome(dto.getNome());
        entity.setMae(mae);
        entity.setStatus(dto.getStatusPato());
        return entity;
    }

    private PatoResponse entityToResponse(Pato entity){
        return PatoResponse.builder()
                .idPato(entity.getIdPato())
                .nome(entity.getNome())
                .idMae(entity.getMae() != null ? entity.getMae().getIdPato() : null)
                .statusPato(entity.getStatus())
                .build();
    }

}
