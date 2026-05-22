package com.fiap.descartecerto.repository;

import com.fiap.descartecerto.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório base criado pelo Samuel (domínio Agendamentos).
// A Lais (domínio Materiais) pode adicionar seus métodos específicos aqui.
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
