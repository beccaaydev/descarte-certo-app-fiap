package com.fiap.descartecerto.repository;

import com.fiap.descartecerto.model.Ecoponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EcopontoRepository extends JpaRepository<Ecoponto,Long > {
    Optional<Ecoponto> findByNome(String nome);

    String nome(String nome);
}
