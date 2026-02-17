package com.br.preco.justo.repository;

import com.br.preco.justo.model.Pato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatoRepository extends JpaRepository<Pato, Long> {

}
