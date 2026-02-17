package com.br.preco.justo.mock;

import com.br.preco.justo.dto.VendedorDto;
import com.br.preco.justo.model.Vendedor;

public class VendedorMock {

    public static VendedorDto createValidVendedorDto(){
        return VendedorDto.builder()
                .nome("Julio Silva")
                .matricula("11234")
                .cpf("111.000.195-01")
                .build();
    }

    public static Vendedor createVendedorEntity() {
        return Vendedor.builder()
                .idVendedor(1L)
                .nome("Julio Silva")
                .matricula("11234")
                .cpf("111.000.195-01")
                .build();
    }
}
