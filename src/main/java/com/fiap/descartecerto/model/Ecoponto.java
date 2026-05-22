package com.fiap.descartecerto.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ECOPONTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ecoponto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ecoponto")
    private Long idEcoponto;
    
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    
    @Column(name = "endereco", nullable = false, length = 255)
    private String endereco;

    @Column(name = "cep")
    private String cepEcoponto;

    @Column(name="bairro", nullable = false)
    private String bairro;

    @Column(name="cidade", nullable = false)
    private String cidade;

    @Column(name="uf", nullable = false)
    private String uf;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Transient
    private double distanciaEmKm;
    
    @Column(name = "horario_funcionamento", length = 100)
    private String horarioFuncionamento;
    
    @Column(name = "data_criacao", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao", insertable = false, updatable = false)
    private LocalDateTime dataAtualizacao;
}


//// TESTE PARA CREATE
//{
//        "nome": "Ecoponto Cidade Baixa",
//        "endereco": "Rua da República, 850",
//        "latitude": -30.040512,
//        "longitude": -51.221458,
//        "horarioFuncionamento": "Segunda a Sábado, das 07:00 às 20:00"
//        }
//
//// TESTE PARA UPDATE
//        {
//        "nome": "Ecoponto Zona Norte",
//        "endereco": "Rua Assis Brasil, 3200",
//        "cepEcoponto": "91140001",
//        "cidade": "Porto Alegre",
//        "bairro": "Sarandi",
//        "uf": "RS",
//        "horarioFuncionamento": "Terça a Domingo, das 07:00 às 18:00"
//        }
