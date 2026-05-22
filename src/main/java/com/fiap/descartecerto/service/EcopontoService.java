package com.fiap.descartecerto.service;


import com.fiap.descartecerto.dto.EcopontoDTO;
import com.fiap.descartecerto.model.Ecoponto;
import com.fiap.descartecerto.model.Usuario;
import com.fiap.descartecerto.repository.EcopontoRepository;
import com.fiap.descartecerto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

import com.fiap.descartecerto.model.Agendamento;



@Service
public class EcopontoService{

    @Autowired
    private EcopontoRepository ecopontoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;



    public EcopontoDTO criarEcoPonto(EcopontoDTO ecoPontoDTO) {
        String ecopontoNome = ecoPontoDTO.getNome();

        if (ecopontoRepository.findByNome(ecopontoNome).isPresent()) {
            throw new IllegalArgumentException("O ecoponto '" + ecopontoNome + "' já existe!");
        }

        Ecoponto ecoponto = new Ecoponto();
        ecoponto.setNome(ecopontoNome);
        ecoponto.setEndereco(ecoPontoDTO.getEndereco());
        ecoponto.setCepEcoponto(ecoPontoDTO.getCepEcoponto());
        ecoponto.setCidade(ecoPontoDTO.getCidade());
        ecoponto.setBairro(ecoPontoDTO.getBairro());
        ecoponto.setUf(ecoPontoDTO.getUf());
        ecoponto.setHorarioFuncionamento(ecoPontoDTO.getHorarioFuncionamento());
        ecoponto.setDataCriacao(LocalDateTime.now());
        ecoponto.setDataAtualizacao(LocalDateTime.now());


        Ecoponto ecopontoSalvo = ecopontoRepository.save(ecoponto);
        return converterParaDTO(ecopontoSalvo);

    }

    public EcopontoDTO atualizarEcoponto(Long idEcoponto, EcopontoDTO ecopontoDTO) {
        Ecoponto ecoponto = ecopontoRepository.findById(idEcoponto)
                .orElseThrow(() -> new IllegalArgumentException("Ecoponto não encontrado com o nome: " + idEcoponto));

        if(ecopontoDTO.getNome() != null) {
            ecoponto.setNome(ecopontoDTO.getNome());
        }
        if(ecopontoDTO.getCidade() != null) {
            ecoponto.setCidade(ecopontoDTO.getCidade());
        }
        if(ecopontoDTO.getEndereco() != null) {
            ecoponto.setEndereco(ecopontoDTO.getEndereco());
        }
        if(ecopontoDTO.getCepEcoponto() != null) {
            ecoponto.setCepEcoponto(ecopontoDTO.getCepEcoponto());
        }
        if(ecopontoDTO.getBairro() != null) {
            ecoponto.setBairro(ecopontoDTO.getBairro());
        }
        if(ecopontoDTO.getUf() != null) {
            ecoponto.setUf(ecopontoDTO.getUf());
        }
        if(ecopontoDTO.getDataCriacao() != null) {
            ecoponto.setDataCriacao(ecopontoDTO.getDataCriacao());
        }
        if(ecopontoDTO.getDataAtualizacao() != null) {
            ecoponto.setDataAtualizacao(LocalDateTime.now());
        }
        Ecoponto ecopontoSalvo = ecopontoRepository.save(ecoponto);
        return converterParaDTO(ecopontoSalvo);
    }






    public EcopontoDTO converterParaDTO(Ecoponto ecoponto) {
        return new EcopontoDTO(
                ecoponto.getIdEcoponto(),
                ecoponto.getNome(),
                ecoponto.getEndereco(),
                ecoponto.getCepEcoponto(),
                ecoponto.getCidade(),
                ecoponto.getBairro(),
                ecoponto.getUf(),
                ecoponto.getHorarioFuncionamento(),
                ecoponto.getDataAtualizacao(),
                ecoponto.getDataCriacao()
        );
    }


      //@param agendamento agendamento concluído
    public void processarAgendamentoConcluido(Agendamento agendamento) {
        if (agendamento == null) return;

        Usuario usuario = agendamento.getUsuario();
        if (usuario == null) return;

        com.fiap.descartecerto.model.Material material = agendamento.getMaterial();

        // Estimativas de peso médio por categoria (kg) e fator de CO2 evitado por kg
        Map<String, BigDecimal> pesoPorCategoria = new HashMap<>();
        Map<String, BigDecimal> co2PorKgPorCategoria = new HashMap<>();

        pesoPorCategoria.put("Plástico", new BigDecimal("1.0"));
        co2PorKgPorCategoria.put("Plástico", new BigDecimal("2.5"));

        pesoPorCategoria.put("Papel", new BigDecimal("0.5"));
        co2PorKgPorCategoria.put("Papel", new BigDecimal("1.0"));

        pesoPorCategoria.put("Vidro", new BigDecimal("2.0"));
        co2PorKgPorCategoria.put("Vidro", new BigDecimal("0.3"));

        pesoPorCategoria.put("Eletrônico", new BigDecimal("0.2"));
        co2PorKgPorCategoria.put("Eletrônico", new BigDecimal("5.0"));

        pesoPorCategoria.put("Químico", new BigDecimal("1.0"));
        co2PorKgPorCategoria.put("Químico", new BigDecimal("0.5"));

        BigDecimal defaultPeso = new BigDecimal("1.0");
        BigDecimal defaultCo2PerKg = new BigDecimal("1.0");

        String categoria = (material != null && material.getCategoria() != null) ? material.getCategoria() : "";
        BigDecimal pesoEstimado = pesoPorCategoria.getOrDefault(categoria, defaultPeso);
        BigDecimal co2PorKg = co2PorKgPorCategoria.getOrDefault(categoria, defaultCo2PerKg);

        BigDecimal co2Evitado = pesoEstimado.multiply(co2PorKg);

        // atualizar totalKgReciclado
        BigDecimal totalKgAnterior = usuario.getTotalKgReciclado() != null ? usuario.getTotalKgReciclado() : BigDecimal.ZERO;
        BigDecimal novoTotalKg = totalKgAnterior.add(pesoEstimado);
        usuario.setTotalKgReciclado(novoTotalKg);

        // atualizar totalCo2Evitado
        BigDecimal totalCo2Anterior = usuario.getTotalCo2Evitado() != null ? usuario.getTotalCo2Evitado() : BigDecimal.ZERO;
        BigDecimal novoTotalCo2 = totalCo2Anterior.add(co2Evitado);
        usuario.setTotalCo2Evitado(novoTotalCo2);

        usuarioRepository.save(usuario);
    }



}
