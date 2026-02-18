package com.br.preco.justo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Table(name = "t_pj_cliente")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    @Column(nullable = false, name = "nm_cliente")
    private String nome;
    @Column(nullable = false, name = "fl_desconto")
    private Boolean flDesconto;
    @Column(nullable = false, unique = true, name = "nr_cpf")
    private String cpf;
    @Column(name = "dt_cadastro", updatable = false)
    private Instant dataCadastro;


}
