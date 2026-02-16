package com.br.preco.justo.service;

import com.br.preco.justo.dto.ClienteDto;
import com.br.preco.justo.exception.ClienteNotFoundException;
import com.br.preco.justo.exception.CpfAlreadyRegistered;
import com.br.preco.justo.mock.ClienteMock;
import com.br.preco.justo.model.Cliente;
import com.br.preco.justo.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;


    @Test
    public void shouldCreateUser() {
        ClienteDto dto = ClienteMock.createValidDto();
        Cliente entity = ClienteMock.createValidEntity();
        Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(entity);

        var resultado = service.save(dto);

        Assertions.assertNotNull(resultado.getId());
        Assertions.assertEquals(dto.getNome(), resultado.getNome());

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Cliente.class));
    }

    @Test
    public void shouldThrowExceptionWhenCpfAlreadyExists() {
        ClienteDto dto = ClienteMock.createValidDto();

        Mockito.when(repository.existsByCpfNativo(Mockito.anyString())).thenReturn(true);
        Assertions.assertThrows(CpfAlreadyRegistered.class, () -> {
            service.save(dto);
        });
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void shouldUpdateUser() {
        Cliente entityAntiga = ClienteMock.createValidEntity();
        ClienteDto dtoAtualizacao = ClienteMock.createUpdateDto();
        Long id = entityAntiga.getIdCliente();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entityAntiga));

        Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(entityAntiga);

        service.update(id, dtoAtualizacao);

        ArgumentCaptor<Cliente> captor = ArgumentCaptor.forClass(Cliente.class);

        Mockito.verify(repository).save(captor.capture());
        Cliente clienteEnviadoParaSalvar = captor.getValue();

        Assertions.assertEquals(dtoAtualizacao.getNome(), clienteEnviadoParaSalvar.getNome());
        Assertions.assertEquals(dtoAtualizacao.getCpf(), clienteEnviadoParaSalvar.getCpf());
        Assertions.assertEquals(id, clienteEnviadoParaSalvar.getIdCliente());
    }

    @Test
    public void shouldNotUpdateUser(){
        ClienteDto dtoAtualizacao = ClienteMock.createUpdateDto();
        Long id = 99L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ClienteNotFoundException.class, () -> {
            service.update(id, dtoAtualizacao);
        });

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());

    }

    @Test
    public void shouldDeleteUser() {

        Cliente cliente = ClienteMock.createValidEntity();
        Long id = cliente.getIdCliente();
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(cliente));

        service.delete(id);
        Mockito.verify(repository, Mockito.times(1)).delete(cliente);
    }

    @Test
    void shouldNotDeleteUser(){
        Long id = 99L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ClienteNotFoundException.class, () -> {
            service.delete(id);
        });

        Mockito.verify(repository, Mockito.never()).delete(Mockito.any());
    }
}
