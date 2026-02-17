package com.br.preco.justo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_pj_pato")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pato")
    private Long idPato;


    @Column(name = "nm_pato")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_mae")
    private Pato mae;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_disponibilidade")
    private StatusPato status;

}
