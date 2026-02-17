package com.br.preco.justo.repository;

import com.br.preco.justo.model.Pato;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatoRepository extends JpaRepository<Pato, Long> {

    @Query("SELECT COUNT(p) FROM Pato p WHERE p.mae.idPato = :id")
    int countFilhosPorIdMae(@Param("id") Long id);
}
