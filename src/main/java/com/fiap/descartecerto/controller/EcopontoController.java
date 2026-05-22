package com.fiap.descartecerto.controller;

import com.fiap.descartecerto.dto.EcopontoDTO;
import com.fiap.descartecerto.model.Ecoponto;
import com.fiap.descartecerto.service.CepService;
import com.fiap.descartecerto.service.EcopontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EcopontoController {

    @Autowired
    private CepService cepService;
    @Autowired
    private EcopontoService ecopontoService;

    @PostMapping("/ecopontos")
    public ResponseEntity<EcopontoDTO> criar(@RequestBody EcopontoDTO ecopontoDTO) {
        try {
            EcopontoDTO ecopontoCriado = ecopontoService.criarEcoPonto(ecopontoDTO);
            return ResponseEntity.ok(ecopontoCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/usuarios/{idUsuario}/ecopontos")
    public ResponseEntity<?> findEcopontosPorUsuario(@PathVariable Long idUsuario) {
        try {
            List<Ecoponto> ecopontos = cepService.encontrarEcopontosProximosDoUsuario(idUsuario);

            if (ecopontos.isEmpty()) {
                return ResponseEntity.ok("Nenhum ecoponto encontrado em um raio de 8km para o endereço deste usuário.");
            }

            return ResponseEntity.ok(ecopontos);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/ecopontos/{idEcoponto}")
    public ResponseEntity<EcopontoDTO> atualizar(@PathVariable Long idEcoponto, @RequestBody EcopontoDTO ecopontoDTO) {
        try {
            EcopontoDTO usuarioAtualizado = ecopontoService.atualizarEcoponto(idEcoponto, ecopontoDTO);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }



}