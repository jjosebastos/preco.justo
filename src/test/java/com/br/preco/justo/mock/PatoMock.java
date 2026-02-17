package com.br.preco.justo.mock;

import com.br.preco.justo.dto.PatoDto;
import com.br.preco.justo.model.Pato;
import com.br.preco.justo.model.StatusPato;

public class PatoMock {

    public static PatoDto createValidDuckDto(){
        return PatoDto.builder()
                .nome("Pato A")
                .statusPato(StatusPato.DISPONIVEL)
                .idMae(1L)
                .build();
    }

    public static Pato createDuckNoMae() {
        return Pato.builder()
                .idPato(1L)
                .nome("Pato Matriarca")
                .status(StatusPato.DISPONIVEL)
                .mae(null)
                .build();
    }

    public static Pato createDuckWithMae() {
        Pato mae = createDuckNoMae();
        mae.setIdPato(50L);
        mae.setNome("Dona Pata");
        return Pato.builder()
                .idPato(2L)
                .nome("Pato Júnior")
                .status(StatusPato.DISPONIVEL)
                .mae(mae)
                .build();
    }
}
