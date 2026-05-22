package com.fiap.descartecerto.repository;

import com.fiap.descartecerto.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByUsuario_IdUsuarioOrderByDataColetaAsc(Long idUsuario);

    List<Agendamento> findByStatusOrderByDataColetaAsc(String status);

    // Busca agendamentos de um ecoponto por status (ex: 'CONCLUIDO')
    List<Agendamento> findByEcoponto_IdEcopontoAndStatus(Long idEcoponto, String status);

    List<Agendamento> findAllByOrderByDataColetaAsc();
}
