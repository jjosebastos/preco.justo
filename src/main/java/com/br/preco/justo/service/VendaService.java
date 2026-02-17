package com.br.preco.justo.service;

import com.br.preco.justo.dto.VendaDto;
import com.br.preco.justo.dto.VendaResponse;
import com.br.preco.justo.exception.ClienteNotFoundException;
import com.br.preco.justo.exception.PatoNotFoundException;
import com.br.preco.justo.exception.PatosAlreadySoldException;
import com.br.preco.justo.exception.VendedorNotFoundException;
import com.br.preco.justo.model.*;
import com.br.preco.justo.repository.ClienteRepository;
import com.br.preco.justo.repository.PatoRepository;
import com.br.preco.justo.repository.VendaRepository;
import com.br.preco.justo.repository.VendedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final PatoRepository patoRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;

    public VendaService(VendaRepository vendaRepository, PatoRepository patoRepository, ClienteRepository clienteRepository, VendedorRepository vendedorRepository) {
        this.vendaRepository = vendaRepository;
        this.patoRepository = patoRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
    }


    @Transactional
    public VendaResponse fazerVenda(VendaDto dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException(""));
        Vendedor vendedor = vendedorRepository.findById(dto.getIdVendedor())
                .orElseThrow(() -> new VendedorNotFoundException(""));

        List<Pato> patosSelecionados = patoRepository.findAllById(dto.getIdsPatos());

        if (patosSelecionados.size() != dto.getIdsPatos().size()) {
            throw new PatoNotFoundException("");
        }

        BigDecimal subtotal = BigDecimal.ZERO;

        for (Pato pato : patosSelecionados) {
            if (pato.getStatus() == StatusPato.VENDIDO) {
                throw new PatosAlreadySoldException("");
            }
            BigDecimal precoPato = calcularPrecoPato(pato);
            subtotal = subtotal.add(precoPato);


            pato.setStatus(StatusPato.VENDIDO);
            patoRepository.save(pato);
        }


        boolean aplicouDesconto = false;
        BigDecimal valorFinal = subtotal;


        if (Boolean.TRUE.equals(cliente.getFlDesconto())) {
            BigDecimal desconto = subtotal.multiply(new BigDecimal("0.20"));
            valorFinal = subtotal.subtract(desconto);
            aplicouDesconto = true;
        }

        Venda venda = Venda.builder()
                .dataVenda(LocalDateTime.now())
                .cliente(cliente)
                .vendedor(vendedor)
                .valorTotal(valorFinal)
                .flDesconto(aplicouDesconto)
                .build();

        vendaRepository.save(venda);
        return entityToReponse(venda);
    }


    private BigDecimal calcularPrecoPato(Pato pato) {
        int qtdFilhos = patoRepository.countFilhosPorIdMae(pato.getIdPato());

        if (qtdFilhos == 0) return new BigDecimal("70.00");
        if (qtdFilhos == 1) return new BigDecimal("50.00");
        return new BigDecimal("25.00");
    }

    private VendaResponse entityToReponse(Venda venda){
        return VendaResponse.builder()
                .idVenda(venda.getIdVenda())
                .vendedor(venda.getVendedor())
                .cliente(venda.getCliente())
                .flDesconto(venda.getFlDesconto())
                .valorVenda(venda.getValorTotal())
                .dataVenda(venda.getDataVenda())
                .build();
    }
}
