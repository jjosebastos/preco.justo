package com.br.preco.justo.service;

import com.br.preco.justo.dto.PatoDto;
import com.br.preco.justo.exception.PatoNotFoundException;
import com.br.preco.justo.mock.PatoMock;
import com.br.preco.justo.model.Cliente;
import com.br.preco.justo.model.Pato;
import com.br.preco.justo.repository.PatoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PatoServiceTest {


    @Mock
    private PatoRepository repository;

    @InjectMocks
    private PatoService service;

    @Test
    public void shouldCreateDuck(){
        Pato pato = PatoMock.createDuckNoMae();
        PatoDto dto = PatoMock.createValidDuckDto();

        Mockito.when(repository.save(Mockito.any(Pato.class))).thenReturn(pato);

        var result = service.save(dto);

        Assertions.assertNotNull(result.getIdPato());

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Pato.class));

    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        PatoDto patoDto = PatoMock.createValidDuckDto();
        patoDto.setNome(null);

        Mockito.when(repository.save(Mockito.any(Pato.class)))
                .thenThrow(new DataIntegrityViolationException("Nome não pode ser nulo"));
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            service.save(patoDto);
        });
    }

    @Test
    void shouldUpdateDuck(){
        Pato entityOld = PatoMock.createDuckNoMae();
        PatoDto dtoUpdate = PatoMock.createValidDuckDto();

        Long entityId = entityOld.getIdPato();

        Mockito.when(repository.findById(entityId)).thenReturn(Optional.of(entityOld));
        Mockito.when(repository.save(Mockito.any(Pato.class))).thenReturn(entityOld);

        service.update(entityId, dtoUpdate);

        ArgumentCaptor<Pato> captor = ArgumentCaptor.forClass(Pato.class);

        Mockito.verify(repository).save(captor.capture());
        var patoEnviadoParaSalvar = captor.getValue();

        Assertions.assertEquals(dtoUpdate.getNome(), patoEnviadoParaSalvar.getNome());
        Assertions.assertEquals(dtoUpdate.getStatusPato(), patoEnviadoParaSalvar.getStatus());
        Assertions.assertEquals(dtoUpdate.getIdMae(), patoEnviadoParaSalvar.getIdPato());

    }

    @Test
    void shouldNotUpdateDuck(){
        PatoDto patoDto = PatoMock.createValidDuckDto();
        Long id = 999L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(PatoNotFoundException.class, ()-> {
            service.update(id, patoDto);
        });

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }
}
