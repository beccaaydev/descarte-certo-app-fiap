package com.fiap.descartecerto.controller;

import com.fiap.descartecerto.dto.AgendamentoDTO;
import com.fiap.descartecerto.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    // POST /agendamentos — Agendar a coleta de um material em um ecoponto
    @PostMapping
    public ResponseEntity<AgendamentoDTO> criar(@RequestBody @Valid AgendamentoDTO dto) {
        try {
            AgendamentoDTO criado = agendamentoService.criarAgendamento(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /agendamentos — Listar todos os agendamentos
    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> listarTodos() {
        List<AgendamentoDTO> agendamentos = agendamentoService.listarTodos();
        return ResponseEntity.ok(agendamentos);
    }

    // GET /agendamentos/{id} — Buscar um agendamento específico
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> buscarPorId(@PathVariable Long id) {
        try {
            AgendamentoDTO agendamento = agendamentoService.buscarPorId(id);
            return ResponseEntity.ok(agendamento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /agendamentos/usuario/{id} — Listar histórico de coletas do usuário
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<AgendamentoDTO>> listarPorUsuario(@PathVariable Long id) {
        try {
            List<AgendamentoDTO> agendamentos = agendamentoService.listarPorUsuario(id);
            return ResponseEntity.ok(agendamentos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /agendamentos/{id} — Alterar a data e/ou o status do agendamento
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> atualizar(
            @PathVariable Long id,
            @RequestBody AgendamentoDTO dto) {
        try {
            AgendamentoDTO atualizado = agendamentoService.atualizarAgendamento(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /agendamentos/{id} — Cancelar um agendamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        try {
            agendamentoService.cancelarAgendamento(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
