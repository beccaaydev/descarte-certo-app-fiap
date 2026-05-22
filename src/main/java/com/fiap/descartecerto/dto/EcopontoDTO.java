package com.fiap.descartecerto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcopontoDTO {
    private Long idEcoponto;
    private String nome;
    private String endereco;
    private String cepEcoponto;
    private String cidade;
    private String bairro;
    private String uf;
    private String horarioFuncionamento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
