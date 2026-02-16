package com.br.preco.justo.repository;


import com.br.preco.justo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT EXISTS (SELECT 1 FROM t_pj_cliente WHERE nr_cpf = :cpf)", nativeQuery = true)
    boolean existsByCpfNativo(@Param("cpf") String cpf);
}
