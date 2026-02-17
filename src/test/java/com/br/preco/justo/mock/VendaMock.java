package com.br.preco.justo.mock;

import com.br.preco.justo.dto.VendaDto;
import com.br.preco.justo.model.Cliente;
import com.br.preco.justo.model.Venda;
import com.br.preco.justo.model.Vendedor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaMock {



    public static Venda createValidEntity(){
        Cliente cliente = ClienteMock.createValidEntity();
        Vendedor vendedor = VendedorMock.createVendedorEntity();
        return Venda.builder()
                .idVenda(1L)
                .dataVenda(LocalDateTime.now())
                .cliente(cliente)
                .vendedor(vendedor)
                .build();
    }

    public static VendaDto createValidDto(){
        Cliente cliente = ClienteMock.createValidEntity();
        Vendedor vendedor = VendedorMock.createVendedorEntity();
        return VendaDto.builder()
                .idCliente(1L)
                .idVendedor(1L)
                .idsPatos(List.of(1L,2L,3L))
                .build();
    }
}
