package com.br.preco.justo.repository;

import com.br.preco.justo.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long > {

    @Query(value = "SELECT EXISTS (SELECT 1 FROM t_pj_vendedor WHERE nr_cpf = :cpf)", nativeQuery = true)
    boolean existsCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM t_pj_vendedor WHERE nr_matricula = :matricula)", nativeQuery = true)
    boolean existsMatricula(@Param("matricula") String matricula);
}
