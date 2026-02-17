package com.br.preco.justo.service;


import com.br.preco.justo.dto.VendedorDto;
import com.br.preco.justo.exception.CpfAlreadyRegistered;
import com.br.preco.justo.exception.MatriculaAlreadyRegistered;
import com.br.preco.justo.exception.VendedorNotFoundException;
import com.br.preco.justo.mock.VendedorMock;
import com.br.preco.justo.model.Vendedor;
import com.br.preco.justo.repository.VendedorRepository;
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
public class VendedorServiceTest {


    @Mock
    private VendedorRepository repository;


    @InjectMocks
    private VendedorService service;


    @Test
    public void shouldCreateVendedor(){
        Vendedor entity = VendedorMock.createVendedorEntity();
        VendedorDto dto = VendedorMock.createValidVendedorDto();
        Mockito.when(repository.save(Mockito.any(Vendedor.class))).thenReturn(entity);

        var result = service.save(dto);

        Assertions.assertNotNull(result.getIdVendedor());
        Assertions.assertEquals(dto.getCpf(), result.getCpf());

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Vendedor.class));
    }

    @Test
    public void shouldNotCreateMatriculaRule(){
        VendedorDto dto = VendedorMock.createValidVendedorDto();
        Mockito.when(repository.existsMatricula(Mockito.anyString())).thenReturn(true);
        Assertions.assertThrows(MatriculaAlreadyRegistered.class, () ->{
            service.save(dto);
        });

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void shouldNotCreateCpfRule(){
        VendedorDto dto = VendedorMock.createValidVendedorDto();
        Mockito.when(repository.existsCpf(Mockito.anyString())).thenReturn(true);
        Assertions.assertThrows(CpfAlreadyRegistered.class, () ->{
            service.save(dto);
        });
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());

    }

    @Test
    public void shouldUpdateVendedor(){
        Vendedor entity = VendedorMock.createVendedorEntity();
        VendedorDto dto = VendedorMock.createValidVendedorDto();
        Long id = entity.getIdVendedor();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));

        Mockito.when(repository.save(Mockito.any(Vendedor.class))).thenReturn(entity);

        service.update(id, dto);

        ArgumentCaptor<Vendedor> captor = ArgumentCaptor.forClass(Vendedor.class);

        Mockito.verify(repository).save(captor.capture());
        Vendedor vendedor = captor.getValue();

        Assertions.assertEquals(dto.getCpf(), vendedor.getCpf());
        Assertions.assertEquals(dto.getNome(), vendedor.getNome());
        Assertions.assertEquals(dto.getMatricula(), vendedor.getMatricula());


    }


    @Test
    public void shouldNotUpdateVendedor(){
        VendedorDto dto = VendedorMock.createValidVendedorDto();
        Long id = 99L;


        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(VendedorNotFoundException.class, () -> {
            service.update(id, dto);
        });

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }
}
