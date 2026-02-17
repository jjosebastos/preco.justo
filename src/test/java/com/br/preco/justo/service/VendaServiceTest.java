package com.br.preco.justo.service;

import com.br.preco.justo.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {

    @Mock
    private VendaRepository repository;

    @InjectMocks
    private VendaService service;


    @Test
    void shouldGenerateVenda(){

    }
}
