package com.br.preco.justo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_pj_vendedor")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Long idVendedor;

    @Column(name = "nm_vendedor", nullable = false)
    private String nome;

    @Column(name = "nr_matricula", nullable = false)
    private String matricula;

    @Column(name = "nr_cpf", nullable = false, length = 15)
    private String cpf;

}
