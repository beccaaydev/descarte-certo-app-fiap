package com.fiap.descartecerto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "cep", nullable = false)
    private String cepUsuario;
    
    @Column(name = "total_kg_reciclado", precision = 10, scale = 2)
    private BigDecimal totalKgReciclado;
    
    @Column(name = "total_co2_evitado", precision = 10, scale = 2)
    private BigDecimal totalCo2Evitado;
    
    @Column(name = "data_criacao", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao", insertable = false, updatable = false)
    private LocalDateTime dataAtualizacao;
}

