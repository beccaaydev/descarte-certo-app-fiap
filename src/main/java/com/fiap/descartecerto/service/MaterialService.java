package com.fiap.descartecerto.service;

import com.fiap.descartecerto.dto.MaterialDTO;
import com.fiap.descartecerto.exception.ErrorResponse;
import com.fiap.descartecerto.model.Agendamento;
import com.fiap.descartecerto.model.Material;
import com.fiap.descartecerto.repository.AgendamentoRepository;
import com.fiap.descartecerto.repository.MaterialRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    public List<MaterialDTO> lsitarTodos(){
        return materialRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    public MaterialDTO buscarPorId(Long id) {
        Material entity = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado com ID:" + id));
        return convertToDTO(entity);
    }

    public MaterialDTO criar(MaterialDTO dto) {
        Material entity = new Material();
        BeanUtils.copyProperties(dto, entity);
        //entity.setIdMaterial(null);
        return convertToDTO(materialRepository.save(entity));
    }

    public MaterialDTO atualizar(Long id, MaterialDTO dto) {
        Material entity = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado com ID: " + id));

        BeanUtils.copyProperties(dto, entity, "idMaterial", "dataCriacao", "dataAtualizacao");
        return convertToDTO(materialRepository.save(entity));
    }

    public void excluir(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new RuntimeException("Não é possível excluir: Material inexistente.");
        }
        materialRepository.deleteById(id);
    }

    public List<Agendamento> listarTodosAgendamentos(){
        return agendamentoRepository.findAll();
    }

    private MaterialDTO convertToDTO(Material entity){
        MaterialDTO dto = new MaterialDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
