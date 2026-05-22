package com.fiap.descartecerto.controller;

import com.fiap.descartecerto.dto.MaterialDTO;

import com.fiap.descartecerto.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialController {
    @Autowired
    private MaterialService service;

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> listar(){
        return ResponseEntity.ok(service.lsitarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> buscar(@PathVariable Long id){
        MaterialDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MaterialDTO> cadastrar(@RequestBody MaterialDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> atualizar(@PathVariable Long id, @RequestBody MaterialDTO dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
