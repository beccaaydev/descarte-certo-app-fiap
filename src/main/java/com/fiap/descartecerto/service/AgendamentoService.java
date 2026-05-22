package com.fiap.descartecerto.service;

import com.fiap.descartecerto.dto.AgendamentoDTO;
import com.fiap.descartecerto.model.Agendamento;
import com.fiap.descartecerto.model.Ecoponto;
import com.fiap.descartecerto.model.Material;
import com.fiap.descartecerto.model.Usuario;
import com.fiap.descartecerto.repository.AgendamentoRepository;
import com.fiap.descartecerto.repository.EcopontoRepository;
import com.fiap.descartecerto.repository.MaterialRepository;
import com.fiap.descartecerto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private static final List<String> STATUS_VALIDOS = List.of("PENDENTE", "CONFIRMADO", "CONCLUIDO", "CANCELADO");

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private EcopontoRepository ecopontoRepository;

    @Autowired
    private EcopontoService ecopontoService;

    public AgendamentoDTO criarAgendamento(AgendamentoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário não encontrado com ID: " + dto.getIdUsuario()));

        Material material = materialRepository.findById(dto.getIdMaterial())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Material não encontrado com ID: " + dto.getIdMaterial()));

        Ecoponto ecoponto = ecopontoRepository.findById(dto.getIdEcoponto())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Ecoponto não encontrado com ID: " + dto.getIdEcoponto()));

        Agendamento agendamento = new Agendamento();
        agendamento.setUsuario(usuario);
        agendamento.setMaterial(material);
        agendamento.setEcoponto(ecoponto);
        agendamento.setDataColeta(dto.getDataColeta());
        agendamento.setStatus("PENDENTE"); // status inicial sempre PENDENTE

        Agendamento salvo = agendamentoRepository.save(agendamento);
        return converterParaDTO(salvo);
    }

    public List<AgendamentoDTO> listarTodos() {
        return agendamentoRepository.findAllByOrderByDataColetaAsc()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<AgendamentoDTO> listarPorUsuario(Long idUsuario) {
        // Valida se o usuário existe antes de buscar
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + idUsuario);
        }

        return agendamentoRepository.findByUsuario_IdUsuarioOrderByDataColetaAsc(idUsuario)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public AgendamentoDTO buscarPorId(Long idAgendamento) {
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Agendamento não encontrado com ID: " + idAgendamento));
        return converterParaDTO(agendamento);
    }

    public AgendamentoDTO atualizarAgendamento(Long idAgendamento, AgendamentoDTO dto) {
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Agendamento não encontrado com ID: " + idAgendamento));

        if ("CANCELADO".equals(agendamento.getStatus())) {
            throw new IllegalArgumentException(
                    "Não é possível alterar um agendamento que já foi cancelado.");
        }

        if (dto.getDataColeta() != null) {
            agendamento.setDataColeta(dto.getDataColeta());
        }

        String statusAntigo = agendamento.getStatus();
        if (dto.getStatus() != null) {
            String novoStatus = dto.getStatus().toUpperCase();
            if (!STATUS_VALIDOS.contains(novoStatus)) {
                throw new IllegalArgumentException(
                        "Status inválido. Os valores aceitos são: " + STATUS_VALIDOS);
            }
            agendamento.setStatus(novoStatus);
        }

        Agendamento atualizado = agendamentoRepository.save(agendamento);

        // se o agendamento passou para CONCLUIDO agora, processar impacto incremental
        if (dto.getStatus() != null && "CONCLUIDO".equalsIgnoreCase(dto.getStatus()) && !"CONCLUIDO".equalsIgnoreCase(statusAntigo)) {
            // atualizar métricas do usuário relacionadas a esse agendamento
            ecopontoService.processarAgendamentoConcluido(atualizado);
        }

        return converterParaDTO(atualizado);
    }

    public void cancelarAgendamento(Long idAgendamento) {
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Agendamento não encontrado com ID: " + idAgendamento));

        // Não faz delete físico — apenas muda o status para CANCELADO
        if ("CANCELADO".equals(agendamento.getStatus())) {
            throw new IllegalArgumentException("Este agendamento já está cancelado.");
        }

        agendamento.setStatus("CANCELADO");
        agendamentoRepository.save(agendamento);
    }

    // Converte a entidade Agendamento para o DTO de resposta
    private AgendamentoDTO converterParaDTO(Agendamento agendamento) {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setIdAgendamento(agendamento.getIdAgendamento());

        dto.setIdUsuario(agendamento.getUsuario().getIdUsuario());
        dto.setNomeUsuario(agendamento.getUsuario().getNome());

        dto.setIdMaterial(agendamento.getMaterial().getIdMaterial());
        dto.setNomeMaterial(agendamento.getMaterial().getNome());

        dto.setIdEcoponto(agendamento.getEcoponto().getIdEcoponto());
        dto.setNomeEcoponto(agendamento.getEcoponto().getNome());

        dto.setDataColeta(agendamento.getDataColeta());
        dto.setStatus(agendamento.getStatus());
        dto.setDataCriacao(agendamento.getDataCriacao());
        dto.setDataAtualizacao(agendamento.getDataAtualizacao());

        return dto;
    }
}
